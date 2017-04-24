package org.smanicon.mowitnow.parser;

import org.smanicon.mowitnow.models.LawnMower;

import java.util.List;

class InstructionExecutor {
    private final LawnMower lawnMower;

    public InstructionExecutor(LawnMower lawnMower) {
        this.lawnMower = lawnMower;
    }

    public void execute(List<InstructionSet> instructionSets) {
        for (InstructionSet instructionSet : instructionSets) {
            instructionSet.executeAction(lawnMower);
        }
    }
}
