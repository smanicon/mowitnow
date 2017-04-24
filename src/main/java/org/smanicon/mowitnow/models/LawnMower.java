package org.smanicon.mowitnow.models;

public class LawnMower {
    private Position position;
    private Direction direction;
    private Lawn lawn;

    public LawnMower(Position position, Direction direction, Lawn lawn) {
        this.position = position;
        this.direction = direction;
        this.lawn = lawn;
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
        Position movedPosition = position.movePosition(direction);
        if(lawn.isCellCanBeMowed(movedPosition)) {
            position = movedPosition;
        }
    }

    @Override
    public String toString() {
        return "LawnMower{" + "position=" + position + ", direction=" + direction + '}';
    }
}
