package org.smanicon.mowitnow.parser;

import org.smanicon.mowitnow.models.LawnMower;

enum InstructionSet {
    G {
        @Override
        public void executeAction(LawnMower lawnMower) {
            lawnMower.turnLeft();
        }
    },

    A {
        @Override
        public void executeAction(LawnMower lawnMower) {
            lawnMower.move();
        }
    },

    D {
        @Override
        public void executeAction(LawnMower lawnMower) {
            lawnMower.turnRight();
        }
    };

    public abstract void executeAction(LawnMower lawnMower);
}
