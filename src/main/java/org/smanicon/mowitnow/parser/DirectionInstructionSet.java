package org.smanicon.mowitnow.parser;

import org.smanicon.mowitnow.models.Direction;

enum DirectionInstructionSet {
    N(Direction.NORTH),
    S(Direction.SOUTH),
    E(Direction.EAST),
    W(Direction.WEST);

    private final Direction direction;

    DirectionInstructionSet(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
}
