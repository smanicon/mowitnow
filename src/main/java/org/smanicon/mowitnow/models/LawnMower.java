package org.smanicon.mowitnow.models;

public class LawnMower {
    private Position position;
    private Direction direction;

    public LawnMower(Position position, Direction direction) {
        this.position = position;
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

    public Position getPosition() {
        return position;
    }

    public void move() {
        position = position.movePosition(direction);
    }

}
