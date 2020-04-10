package net.coderdojo.neusiedl;

import net.coderdojo.neusiedl.gameengine.math.Point;
import net.coderdojo.neusiedl.gameengine.math.Vector;

import java.awt.Color;

public class Catcher extends Ball {
    public Catcher(Point centerPoint, int diameter, Vector movementVector, Color color) {
        super(centerPoint, diameter, movementVector, color);
    }

    public void moveLeft() {
        System.out.println("left");
    }

    public void moveRight() {
        System.out.println("right");
    }

    public void stop() {
        System.out.println("stop");
    }
}
