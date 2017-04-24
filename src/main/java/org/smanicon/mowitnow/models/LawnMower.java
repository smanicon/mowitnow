package org.smanicon.mowitnow.models;

public class LawnMower {
    private Direction direction;

    public LawnMower(Direction direction) {
        this.direction = direction;
    }

    public void turnRight() {
        direction = direction.getRight();
    }

    public void turnLeft() {
        direction = direction.getLeft();
    }

    public Direction getDirection() {
        return direction;
    }
}
