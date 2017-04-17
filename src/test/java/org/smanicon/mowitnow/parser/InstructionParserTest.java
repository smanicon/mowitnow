package org.smanicon.mowitnow.parser;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.smanicon.mowitnow.models.*;

import java.io.*;

public class InstructionParserTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void should_return_a_bounded_lawn_6x7() throws IOException, InstructionParserException {
        InstructionParser parser = InstructionParser.parse(new StringReader("6 7"));
        BoundedLawn lawn = parser.parseBoundedLawn();

        Assertions.assertThat(lawn.getWidth()).isEqualTo(6);
        Assertions.assertThat(lawn.getHeight()).isEqualTo(7);
    }

    @Test
    public void should_return_a_bounded_lawn_4x5() throws IOException, InstructionParserException {
        InstructionParser parser = InstructionParser.parse(new StringReader("4 5"));
        BoundedLawn lawn = parser.parseBoundedLawn();

        Assertions.assertThat(lawn.getWidth()).isEqualTo(4);
        Assertions.assertThat(lawn.getHeight()).isEqualTo(5);
    }

    @Test
    public void should_throw_exception_when_creation_lawn_line_have_3_arguments() throws IOException, InstructionParserException {
        InstructionParser parser = InstructionParser.parse(new StringReader("4 5 6"));

        thrown.expect(InstructionParserException.class);
        thrown.expectMessage("Can't create Lawn : 2 arguments expected, 3 found - at line [4 5 6]");

        parser.parseBoundedLawn();
    }

    @Test
    public void should_throw_exception_if_lawn_width_is_invalid_number() throws IOException, InstructionParserException {
        InstructionParser parser = InstructionParser.parse(new StringReader("a 5"));

        thrown.expect(InstructionParserException.class);
        thrown.expectMessage("Can't create Lawn : 'a' is an invalid number for width - at line [a 5]");

        parser.parseBoundedLawn();
    }

    @Test
    public void should_throw_exception_if_lawn_width_is_negative() throws IOException, InstructionParserException {
        InstructionParser parser = InstructionParser.parse(new StringReader("-5 5"));

        thrown.expect(InstructionParserException.class);
        thrown.expectMessage("Can't create Lawn : width can't be negative '-5' - at line [-5 5]");

        parser.parseBoundedLawn();
    }

    @Test
    public void should_throw_exception_if_lawn_height_is_invalid_number() throws IOException, InstructionParserException {
        InstructionParser parser = InstructionParser.parse(new StringReader("5 a"));

        thrown.expect(InstructionParserException.class);
        thrown.expectMessage("Can't create Lawn : 'a' is an invalid number for height - at line [5 a]");

        parser.parseBoundedLawn();
    }

    @Test
    public void should_throw_exception_if_lawn_height_is_negative() throws IOException, InstructionParserException {
        InstructionParser parser = InstructionParser.parse(new StringReader("5 -5"));

        thrown.expect(InstructionParserException.class);
        thrown.expectMessage("Can't create Lawn : height can't be negative '-5' - at line [5 -5]");

        parser.parseBoundedLawn();
    }
}
