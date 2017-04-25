package org.smanicon.mowitnow.parser;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.smanicon.mowitnow.models.*;

import java.io.*;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.smanicon.mowitnow.parser.InstructionSet.*;

public class InstructionParserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private InstructionParser parser = null;

    @After
    public void tearDown() throws IOException {
       if(parser != null) {
               parser.close();
       }
    }

    private void buildParserFromString(String s) throws IOException {
        parser = InstructionParser.parse(new StringReader(s));
    }

    @Test
    public void should_return_a_bounded_lawn_6x7() throws IOException, InstructionParserException {
        buildParserFromString("6 7");
        BoundedLawn lawn = parser.parseBoundedLawn();

        assertThat(lawn.getWidth()).isEqualTo(6);
        assertThat(lawn.getHeight()).isEqualTo(7);
    }

    @Test
    public void should_return_a_bounded_lawn_4x5() throws IOException, InstructionParserException {
        buildParserFromString("4 5");
        BoundedLawn lawn = parser.parseBoundedLawn();

        assertThat(lawn.getWidth()).isEqualTo(4);
        assertThat(lawn.getHeight()).isEqualTo(5);
    }

    @Test
    public void should_throw_exception_when_creation_lawn_line_have_3_arguments() throws IOException, InstructionParserException {
        buildParserFromString("4 5 6");

        thrown.expect(InstructionParserException.class);
        thrown.expectMessage("Can't create Lawn : 2 arguments expected, 3 found - at line [4 5 6]");

        parser.parseBoundedLawn();
    }

    @Test
    public void should_throw_exception_if_lawn_width_is_invalid_number() throws IOException, InstructionParserException {
        buildParserFromString("a 5");

        thrown.expect(InstructionParserException.class);
        thrown.expectMessage("Can't create Lawn : 'a' is an invalid number for width - at line [a 5]");

        parser.parseBoundedLawn();
    }

    @Test
    public void should_throw_exception_if_lawn_width_is_negative() throws IOException, InstructionParserException {
        buildParserFromString("-5 5");

        thrown.expect(InstructionParserException.class);
        thrown.expectMessage("Can't create Lawn : width can't be negative '-5' - at line [-5 5]");

        parser.parseBoundedLawn();
    }

    @Test
    public void should_throw_exception_if_lawn_height_is_invalid_number() throws IOException, InstructionParserException {
        buildParserFromString("5 a");

        thrown.expect(InstructionParserException.class);
        thrown.expectMessage("Can't create Lawn : 'a' is an invalid number for height - at line [5 a]");

        parser.parseBoundedLawn();
    }

    @Test
    public void should_throw_exception_if_lawn_height_is_negative() throws IOException, InstructionParserException {
        buildParserFromString("5 -5");

        thrown.expect(InstructionParserException.class);
        thrown.expectMessage("Can't create Lawn : height can't be negative '-5' - at line [5 -5]");

        parser.parseBoundedLawn();
    }

    @Test
    public void should_create_lawnmower_positioned_x1_y2_and_oriented_to_the_NORTH() throws IOException, InstructionParserException {
        buildParserFromString("1 2 N");
        LawnMower lawnMower = parser.parseLawnMower(new BoundedLawn(10, 10));

        assertThat(lawnMower.getPosition()).isEqualTo(new Position(1, 2));
        assertThat(lawnMower.getDirection()).isEqualTo(Direction.NORTH);
    }

    @Test
    public void should_create_lawnmower_positioned_x2_y3_and_oriented_to_the_SOUTH() throws IOException, InstructionParserException {
        buildParserFromString("2 3 S");
        LawnMower lawnMower = parser.parseLawnMower(new BoundedLawn(10, 10));

        assertThat(lawnMower.getPosition()).isEqualTo(new Position(2, 3));
        assertThat(lawnMower.getDirection()).isEqualTo(Direction.SOUTH);
    }

    @Test
    public void should_create_lawnmower_positioned_x3_y4_and_oriented_to_the_EAST() throws IOException, InstructionParserException {
        buildParserFromString("3 4 E");
        LawnMower lawnMower = parser.parseLawnMower(new BoundedLawn(10, 10));

        assertThat(lawnMower.getPosition()).isEqualTo(new Position(3, 4));
        assertThat(lawnMower.getDirection()).isEqualTo(Direction.EAST);
    }

    @Test
    public void should_create_lawnmower_positioned_x4_y5_and_oriented_to_the_WEST() throws IOException, InstructionParserException {
        buildParserFromString("4 5 W");
        LawnMower lawnMower = parser.parseLawnMower(new BoundedLawn(10, 10));

        assertThat(lawnMower.getPosition()).isEqualTo(new Position(4, 5));
        assertThat(lawnMower.getDirection()).isEqualTo(Direction.WEST);
    }

    @Test
    public void should_throw_exception_if_creation_lawnmower_line_has_4_arguments() throws IOException, InstructionParserException {
        buildParserFromString("4 5 W V");

        thrown.expect(InstructionParserException.class);
        thrown.expectMessage("Can't create LawnMower : 3 arguments expected, 4 found - at line [4 5 W V]");

        parser.parseLawnMower(new BoundedLawn(10, 10));
    }

    @Test
    public void should_throw_exception_if_lawnmower_x_position_is_invalide_number() throws IOException, InstructionParserException {
        buildParserFromString("a 5 N");

        thrown.expect(InstructionParserException.class);
        thrown.expectMessage("Can't create LawnMower : 'a' is an invalid number for x position - at line [a 5 N]");

        parser.parseLawnMower(new BoundedLawn(10, 10));
    }

    @Test
    public void should_throw_exception_if_lawnmower_y_position_is_invalide_number() throws IOException, InstructionParserException {
        buildParserFromString("5 a N");

        thrown.expect(InstructionParserException.class);
        thrown.expectMessage("Can't create LawnMower : 'a' is an invalid number for y position - at line [5 a N]");

        parser.parseLawnMower(new BoundedLawn(10, 10));
    }

    @Test
    public void should_throw_exception_if_lawnmower_position_is_not_in_valid_lawn_position() throws IOException, InstructionParserException {
        buildParserFromString("5 5 N");

        Lawn lawn = mock(Lawn.class);
        when(lawn.isCellCanBeMowed(any(Position.class))).thenReturn(false);

        thrown.expect(InstructionParserException.class);
        thrown.expectMessage("Can't create LawnMower : Invalid position [Position{x=5, y=5}] in lawn - at line [5 5 N]");

        parser.parseLawnMower(lawn);
    }

    @Test
    public void should_throw_exception_if_lawnmower_direction_is_invalid() throws IOException, InstructionParserException {
        buildParserFromString("5 5 A");

        thrown.expect(InstructionParserException.class);
        thrown.expectMessage("Can't create LawnMower : Invalid direction [A] - at line [5 5 A]");

        parser.parseLawnMower(new BoundedLawn(10, 10));
    }

    @Test
    public void should_parse_instruction_turn_right() throws IOException, InstructionParserException {
        buildParserFromString("D");
        assertThat(parser.parseMowerInstructions()).containsOnly(D);
    }

    @Test
    public void should_parse_instruction_turn_left() throws IOException, InstructionParserException {
        buildParserFromString("G");
        assertThat(parser.parseMowerInstructions()).containsOnly(G);
    }

    @Test
    public void should_parse_instruction_move() throws IOException, InstructionParserException {
        buildParserFromString("A");
        assertThat(parser.parseMowerInstructions()).containsOnly(A);
    }

    @Test
    public void should_throw_exception_if_invalid_instruction_found() throws IOException, InstructionParserException {
        buildParserFromString("F");

        thrown.expect(InstructionParserException.class);
        thrown.expectMessage("Can't create Instruction List : [F] is invalid instruction - at line [F]");

        parser.parseMowerInstructions();
    }

    @Test
    public void should_parse_many_instructions() throws IOException, InstructionParserException {
        buildParserFromString("ADGGDA");
        assertThat(parser.parseMowerInstructions()).containsExactly(A, D, G, G, D, A);
    }

    @Test
    public void should_return_true_if_reader_has_instructions_to_read() throws IOException, InstructionParserException {
        buildParserFromString("FIRST LINE");
        assertThat(parser.hasNextInstruction()).isTrue();
    }

    @Test
    public void should_return_false_if_reader_has_no_instruction_again() throws IOException, InstructionParserException {
        buildParserFromString("5 5");
        parser.parseBoundedLawn();
        assertThat(parser.hasNextInstruction()).isFalse();
    }
}
