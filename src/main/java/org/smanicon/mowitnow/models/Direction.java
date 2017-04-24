package org.smanicon.mowitnow.models;

public enum Direction {
    NORTH(0, 1), SOUTH(0, -1), WEST(-1, 0), EAST(1, 0);

    private Direction right;
    private Direction left;
    private final int displacementX;
    private final int displacementY;

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


    Direction(int displacementX, int displacementY) {
        this.displacementX = displacementX;
        this.displacementY = displacementY;
    }

    public Direction getRight() {
        return right;
    }

    public Direction getLeft() {
        return left;
    }

    public int getDisplacementX() {
        return displacementX;
    }

    public int getDisplacementY() {
        return displacementY;
    }
}
