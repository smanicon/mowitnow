package org.smanicon.mowitnow.models;

public class Lawn {
    private final int width;
    private final int height;

    public Lawn(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    boolean isCellCanBeMowed(Position position) {
        return position.getX() >= 0
                && position.getY() >= 0
                && position.getY() <= getHeight()
                && position.getX() <= getWidth();
    }
}
