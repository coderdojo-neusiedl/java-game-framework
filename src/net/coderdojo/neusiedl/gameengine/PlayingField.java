package net.coderdojo.neusiedl.gameengine;

import net.coderdojo.neusiedl.gameengine.component.MovableComponent;
import net.coderdojo.neusiedl.gameengine.math.Rectangle;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * PlayingField represents the area where the game takes place. Components can get added or removed by calling the
 * corresponding methods add(component) and remove(component).
 * <p>
 * To start the animation the method start() needs to be called. stop() halts the animation.
 * <p>
 * Use setOnNextFrameCallback(callback) to register a callback method that gets called for each frame before the
 * components get painted. This can be used to e.g. move components, detect collisions, react on user input, ...
 */
public class PlayingField extends JPanel {

    private static final long NO_DELAY = 0;
    private static final int MILLISECONDS_PER_SECOND = 1000;
    private static Color BACKGROUND_COLOR = Color.WHITE;

    private final int width;
    private final int height;
    private final Duration framePeriod;
    private BufferedImage image1;
    private BufferedImage image2;
    private AtomicReference<BufferedImage> activeImage = new AtomicReference<>();
    private Collection<MovableComponent> components = Collections.synchronizedCollection(new ArrayList<>());
    private Map<BufferedImage, Collection<Rectangle>> dirtyAreasPerImage = new HashMap<>();
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private Optional<Runnable> onNextFrameCallback = Optional.empty();
    private Optional<ScheduledFuture<?>> scheduled = Optional.empty();

    public PlayingField(int width, int height, int framesPerSecond) {
        this.width = width;
        this.height = height;
        framePeriod = Duration.ofMillis(MILLISECONDS_PER_SECOND / framesPerSecond);
        setPreferredSize(new Dimension(width, height));
        image1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        image2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        activeImage.set(image1);
        dirtyAreasPerImage.put(image1, new ArrayList<>());
        dirtyAreasPerImage.put(image2, new ArrayList<>());
        SwingUtilities.invokeLater(this::paintNextFrame);
    }

    public void add(MovableComponent component) {
        components.add(component);
    }

    public void remove(MovableComponent component) {
        if (components.contains(component)) {
            components.remove(component);
        }
    }

    /**
     * Starts the animation.
     */
    public void start() {
        if (scheduled.isEmpty()) {
            scheduled = Optional.of(scheduler.scheduleAtFixedRate(this::paintNextFrame, NO_DELAY,
                    framePeriod.toMillis(), TimeUnit.MILLISECONDS));
        }
    }

    /**
     * Stops the animation.
     */
    public void stop() {
        scheduled.ifPresent(s -> s.cancel(false));
        scheduled = Optional.empty();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        BufferedImage image = activeImage.get();
        if (image != null) {
            g2d.drawImage(image, 0, 0, null);
        }
    }

    /**
     * setOnNextFrameCallback registers the specified runnable to get called before each component of a frame gets painted.
     *
     * @param runnable the callback that shall get called
     */
    public void setOnNextFrameCallback(Runnable runnable) {
        onNextFrameCallback = Optional.of(runnable);
    }

    private void paintNextFrame() {
        BufferedImage offScreenImage = activeImage.get() == image1 ? image2 : image1;
        Graphics2D graphics = offScreenImage.createGraphics();
        Collection<Rectangle> dirtyAreas = dirtyAreasPerImage.get(offScreenImage);

        graphics.setColor(BACKGROUND_COLOR);
        if (dirtyAreas.isEmpty()) {
            graphics.fillRect(0, 0, width, height);
        } else {
            dirtyAreas.forEach(area -> graphics.fillRect(area.getTopLeftX(), area.getTopLeftY(), area.getWidth(), area.getHeight()));
        }
        dirtyAreas.clear();

        onNextFrameCallback.ifPresent(Runnable::run);

        components.forEach(component -> {
            component.paint(graphics);
            dirtyAreas.add(component.getCoveredArea());
        });

        activeImage.set(offScreenImage);
        repaint();
    }
}
