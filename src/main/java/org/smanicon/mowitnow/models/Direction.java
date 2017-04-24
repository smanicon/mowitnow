package org.smanicon.mowitnow.models;

public enum Direction {
    NORTH, SOUTH, WEST, EAST;

    private Direction right;
    private Direction left;

    static {
        NORTH.right = EAST;
        SOUTH.right = WEST;
        WEST.right = NORTH;
        EAST.right = SOUTH;
    }

    static {
        NORTH.left = WEST;
        SOUTH.left = EAST;
        WEST.left = SOUTH;
        EAST.left = NORTH;
    }

    public Direction getRight() {
        return right;
    }

    public Direction getLeft() {
        return left;
    }
}
