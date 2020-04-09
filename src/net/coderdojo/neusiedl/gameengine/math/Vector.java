package net.coderdojo.neusiedl.gameengine.math;

/**
 * Vector describes a movement in the two dimensional space.
 */
public class Vector {
    public static final Vector NO_MOVEMENT = new Vector(0,0);

    private int x;
    private int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * multiplyWith multiplies the current vector with the specified vector.
     * @param multiplicator the vector that gets multiplied with the current vector
     * @return a new instance of a vector representing the result of the multiplication
     */
    public Vector multiplyWith(Vector multiplicator) {
        return new Vector(x * multiplicator.x, y * multiplicator.y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getLength() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    @Override
    public String toString() {
        return "Vector{" + "x=" + x + ", y=" + y + '}';
    }
}
