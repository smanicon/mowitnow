package org.smanicon.mowitnow.parser;

import org.assertj.core.groups.Tuple;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.smanicon.mowitnow.models.Direction;
import org.smanicon.mowitnow.models.Position;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

public class LawnMowerProgramRunnerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Reader readProgram(String programPath) throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(programPath).getFile());
        return new FileReader(file);
    }

    @Test
    public void should_move_a_lawnmower_from_given_program() throws IOException, InstructionParserException {
        LawnMowerProgramRunner programRunner = new LawnMowerProgramRunner(readProgram("simpleMowerProgram"));

        programRunner.run();
        assertThat(programRunner.getLawnMower())
                .extracting("position", "direction")
                .containsExactly(
                        Tuple.tuple(new Position(2, 3), Direction.NORTH)
                );
    }

    @Test
    public void should_throw_exception_when_instruction_lawnmower_is_missing() throws IOException, InstructionParserException {
        LawnMowerProgramRunner programRunner = new LawnMowerProgramRunner(readProgram("incompleteMowerProgram"));

        thrown.expect(InstructionParserException.class);
        thrown.expectMessage("Error parsing program, instruction missing");
        programRunner.run();
    }

    @Test
    public void should_validate_the_initial_XEBIA_test() throws IOException, InstructionParserException {
        LawnMowerProgramRunner programRunner = new LawnMowerProgramRunner(readProgram("initialTest"));

        programRunner.run();
        assertThat(programRunner.getLawnMower())
                .extracting("position", "direction")
                .containsExactly(
                        Tuple.tuple(new Position(1, 3), Direction.NORTH),
                        Tuple.tuple(new Position(5, 1), Direction.EAST)
                );
    }
}
