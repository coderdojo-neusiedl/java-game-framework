package net.coderdojo.neusiedl.gameengine.math;

/**
 * Point describes a point in a two dimensional space.
 */
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * moveBy moves the point by the specified vector by adding the vector to the point.
     * @param vector the vector to add
     * @return a new instance of a point describing the position after the movement
     */
    public Point moveBy(Vector vector) {
        return new Point(this.x + vector.getX(), this.y + vector.getY());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }
}
