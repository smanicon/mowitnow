package org.smanicon.mowitnow.models;

public class LawnMower {
    private Direction direction;

    public LawnMower(Direction direction) {
        this.direction = direction;
    }

    public void turnRight() {
    }

    public Direction getDirection() {
        return direction.getRight();
    }
}
