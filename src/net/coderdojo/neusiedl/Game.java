package net.coderdojo.neusiedl;

import net.coderdojo.neusiedl.gameengine.*;
import net.coderdojo.neusiedl.gameengine.math.Point;
import net.coderdojo.neusiedl.gameengine.math.Vector;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.security.Key;

public class Game implements Runnable {

    private static final int PLAYING_FIELD_WIDTH = 400;
    private static final int PLAYING_FIELD_HEIGHT = 400;
    private static final int FRAMES_PER_SECOND = 25;
    private static final String WINDOW_TITLE = "DojoGame";
    private static final String START = "start";
    private static final String STOP = "stop";

    private PlayingField playingField;
    private JButton startStopButton;
    private Catcher catcher;

    /**
     * Entry point of the program.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }

    @Override
    public void run() {
        JFrame frame = new JFrame(WINDOW_TITLE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createStartStopButtonInPanel(frame);
        createPlayingField(frame);
        frame.pack();
        frame.setVisible(true);
    }

    private void createPlayingField(JFrame frame) {
        playingField = new PlayingField(PLAYING_FIELD_WIDTH, PLAYING_FIELD_HEIGHT, FRAMES_PER_SECOND);
        Point centerPoint = new Point(PLAYING_FIELD_WIDTH/2, 350);
        int diameter = 20;
        Vector movementVector = Vector.NO_MOVEMENT;
        Color color = Color.BLACK;
         catcher = new Catcher(centerPoint, diameter, movementVector, color);

        playingField.add(catcher);
        new Keyboard(playingField, catcher::moveLeft, catcher::moveRight, catcher::stop);
        playingField.setOnNextFrameCallback(this::onNextFrame);
        frame.add(playingField);

    }

    /**
     * This method gets called immediately before each component of the game gets painted into the next frame.
     * Typical activities in this method are:
     * - moving the components by calling their method move()
     * - collision detection
     * - reacting on user input
     */
    private void onNextFrame() {
        catcher.move();
    }

    private void createStartStopButtonInPanel(JFrame frame) {
        JPanel panel = new JPanel();
        startStopButton = new JButton(START);
        startStopButton.addActionListener(this::startStopButtonClicked);
        panel.add(startStopButton);
        frame.add(panel, BorderLayout.NORTH);
    }

    private void startStopButtonClicked(ActionEvent actionEvent) {
        if (startStopButton.getText().equals(START)) {
            playingField.start();
            startStopButton.setText(STOP);
        } else {
            playingField.stop();
            startStopButton.setText(START);
        }
    }
}
