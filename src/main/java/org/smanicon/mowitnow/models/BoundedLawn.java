package org.smanicon.mowitnow.models;

public class BoundedLawn implements Lawn {
    private final int width;
    private final int height;

    public BoundedLawn(int width, int height) {
        validateParameters(width, height);

        this.width = width;
        this.height = height;
    }

    private void validateParameters(int width, int height) {
        if(width < 0) {
            throw new IllegalArgumentException("width can't be negative");
        }

        if(height < 0) {
            throw new IllegalArgumentException("height can't be negative");
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public boolean isCellCanBeMowed(Position position) {
        return position.getX() >= 0
                && position.getY() >= 0
                && position.getY() <= getHeight()
                && position.getX() <= getWidth();
    }
}
