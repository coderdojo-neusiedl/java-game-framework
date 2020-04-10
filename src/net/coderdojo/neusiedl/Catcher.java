package net.coderdojo.neusiedl;

import net.coderdojo.neusiedl.gameengine.math.Point;
import net.coderdojo.neusiedl.gameengine.math.Vector;

import java.awt.Color;

public class Catcher extends Ball {
    public Catcher(Point centerPoint, int diameter, Vector movementVector, Color color) {
        super(centerPoint, diameter, movementVector, color);
    }

    public void moveLeft() {
        setMovementVector(new Vector(-5, 0));
    }


    public void moveRight() {
        setMovementVector(new Vector(5, 0));
    }

    public void stop() {
        setMovementVector(new Vector(0, 0));
    }
}
