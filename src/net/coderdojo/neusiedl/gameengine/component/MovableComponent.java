package net.coderdojo.neusiedl.gameengine.component;

import net.coderdojo.neusiedl.gameengine.math.Point;
import net.coderdojo.neusiedl.gameengine.math.Rectangle;
import net.coderdojo.neusiedl.gameengine.math.Vector;

import java.awt.Graphics2D;

/**
 * MovableComponent describes a component of the game that can be moved.
 */
public abstract class MovableComponent {
    private Point centerPoint;
    private final int diameter;
    private Vector movementVector;

    /**
     * Creates a new instance of a movable component.
     *
     * @param centerPoint    the center of the component
     * @param diameter       the diameter of the smallest possible circle that fully contains the component
     * @param movementVector the vector describing the movement/displacement of the component from on frame to the next
     */
    public MovableComponent(Point centerPoint, int diameter, Vector movementVector) {
        this.centerPoint = centerPoint;
        this.diameter = diameter;
        this.movementVector = movementVector;
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public int getDiameter() {
        return diameter;
    }

    public Vector getMovementVector() {
        return this.movementVector;
    }

    public void setMovementVector(Vector vector) {
        this.movementVector = vector;
    }

    public void move() {
        centerPoint = centerPoint.moveBy(movementVector);
    }

    /**
     * @return the smallest rectangle that fully contains this component.
     */
    public Rectangle getCoveredArea() {
        int topLeftX = centerPoint.getX() - diameter / 2;
        int topLeftY = centerPoint.getY() - diameter / 2;
        return new Rectangle(topLeftX, topLeftY, diameter, diameter);
    }

    public abstract void paint(Graphics2D graphics);
}
