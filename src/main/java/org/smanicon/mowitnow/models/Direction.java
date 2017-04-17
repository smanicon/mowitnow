package org.smanicon.mowitnow.models;

public enum Direction {
    NORTH, SOUTH, WEST, EAST;

    private Direction right;

    static {
        NORTH.right = EAST;
        SOUTH.right = WEST;
        WEST.right = NORTH;
        EAST.right = SOUTH;
    }

    public Direction getRight() {
        return right;
    }
}
