package net.coderdojo.neusiedl;

import net.coderdojo.neusiedl.gameengine.math.Point;
import net.coderdojo.neusiedl.gameengine.math.Vector;
import net.coderdojo.neusiedl.gameengine.component.MovableComponent;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Ball descibes a movable circle painted with its color.
 */
public class Ball extends MovableComponent {
    private Color color;

    public Ball(Point centerPoint, int diameter, Vector movementVector, Color color) {
        super(centerPoint, diameter, movementVector);
        this.color = color;
    }

    public void paint(Graphics2D graphics) {
        graphics.setColor(color);
        Point centerPoint = getCenterPoint();
        int diameter = getDiameter();
        int x = centerPoint.getX() - diameter / 2;
        int y = centerPoint.getY() - diameter / 2;
        graphics.fillArc(x, y, diameter, diameter, 0, 360);
    }
}
