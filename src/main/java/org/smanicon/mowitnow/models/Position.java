package org.smanicon.mowitnow.models;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position movePosition(Direction direction) {
        return new Position(
                x + direction.getDisplacementX(),
                y + direction.getDisplacementY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        return x == position.x
                && y == position.y;
    }

    @Override
    public int hashCode() {
        return y + 31 * x;
    }
}
