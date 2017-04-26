package org.smanicon.mowitnow.models;

public final class LawnMowerView {

    private final LawnMower lawnMower;

    public LawnMowerView(LawnMower lawnMower) {
        this.lawnMower = lawnMower;
    }

    public Direction getDirection() {
        return lawnMower.getDirection();
    }

    public Position getPosition() {
        return lawnMower.getPosition();
    }
}
