package net.coderdojo.neusiedl.gameengine.math;

/**
 * Describes a rectangular area in q two dimensional space.
 */
public class Rectangle {
    private final int topLeftX;
    private final int topLeftY;
    private final int width;
    private final int height;

    public Rectangle(int topLeftX, int topLeftY, int width, int height) {
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.width = width;
        this.height = height;
    }

    public int getTopLeftX() {
        return topLeftX;
    }

    public int getTopLeftY() {
        return topLeftY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "topLeftX=" + topLeftX +
                ", topLeftY=" + topLeftY +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
