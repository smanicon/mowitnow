package org.smanicon.mowitnow.parser;

import org.smanicon.mowitnow.models.BoundedLawn;
import org.smanicon.mowitnow.models.LawnMower;
import org.smanicon.mowitnow.models.LawnMowerView;

import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class LawnMowerProgramRunner {

    private final Reader programReader;
    private List<LawnMowerView> lawnMowers;

    public LawnMowerProgramRunner(Reader programReader) {
        this.programReader = programReader;
    }

    public void run() throws IOException, InstructionParserException {
        try (InstructionParser parser = InstructionParser.parse(programReader)) {
            BoundedLawn lawnGridBounded = parser.parseBoundedLawn();

            lawnMowers = getLawnMowerViewAfterTheyMoved(parser, lawnGridBounded);
        } catch (NoSuchElementException e) {
            throw new InstructionParserException(e, "Error parsing program, instruction missing", null);
        }
    }

    private List<LawnMowerView> getLawnMowerViewAfterTheyMoved(InstructionParser parser, BoundedLawn lawnGridBounded) throws IOException, InstructionParserException {
        List<LawnMowerView> lawnMowerAfterProgramRan = new LinkedList<>();
        while (parser.hasNextInstruction()) {
            LawnMower lawnMower = runLawnMowerProgram(parser, lawnGridBounded);

            lawnMowerAfterProgramRan.add(new LawnMowerView(lawnMower));
        }
        return lawnMowerAfterProgramRan;
    }

    private LawnMower runLawnMowerProgram(InstructionParser parser, BoundedLawn lawnGridBounded) throws IOException, InstructionParserException {
        LawnMower lawnMower = parser.parseLawnMower(lawnGridBounded);
        List<InstructionSet> instructionSets = parser.parseMowerInstructions();

        InstructionExecutor instructionExecutor = new InstructionExecutor(lawnMower);
        instructionExecutor.execute(instructionSets);

        return lawnMower;
    }

    public List<LawnMowerView> getLawnMower() {
        return Collections.unmodifiableList(lawnMowers);
    }
}
