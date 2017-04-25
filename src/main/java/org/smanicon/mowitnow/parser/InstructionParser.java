package org.smanicon.mowitnow.parser;

import org.smanicon.mowitnow.models.*;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

class InstructionParser implements Closeable {
    private final Scanner scanner;

    public static InstructionParser parse(Reader r) throws IOException {
        return new InstructionParser(new Scanner(r));
    }

    private InstructionParser(Scanner scanner) {
        this.scanner = scanner;
    }

    public BoundedLawn parseBoundedLawn() throws IOException, InstructionParserException {
        return parseBoundedLawn(scanner.nextLine());
    }

    private BoundedLawn parseBoundedLawn(String line) throws InstructionParserException {
        StringTokenizer tokenizer = new StringTokenizer(line);

        checkArgumentCount(tokenizer, 2, line, "Lawn");

        int width = parsePositiveNumber(tokenizer.nextToken(), line, "Lawn", "width");
        int height = parsePositiveNumber(tokenizer.nextToken(), line, "Lawn", "height");

        return new BoundedLawn(width, height);
    }

    public LawnMower parseLawnMower(Lawn lawn) throws IOException, InstructionParserException {
        return parseLawnMower(scanner.nextLine(), lawn);
    }

    private LawnMower parseLawnMower(String line, Lawn lawn) throws InstructionParserException {
        StringTokenizer tokenizer = new StringTokenizer(line);

        checkArgumentCount(tokenizer, 3, line, "LawnMower");

        int x = parseNumber(tokenizer.nextToken(), line, "LawnMower", "x position");
        int y = parseNumber(tokenizer.nextToken(), line, "LawnMower", "y position");

        Position position = new Position(x, y);
        checkLawnPosition(lawn, position, line, "LawnMower");

        Direction direction = parseDirection(tokenizer.nextToken(), line, "LawnMower");

        return new LawnMower(position, direction, lawn);
    }

    private Direction parseDirection(String directionStr, String line, String objectToCreate) throws InstructionParserException {
        try {
            return DirectionInstructionSet.valueOf(directionStr).getDirection();
        } catch (IllegalArgumentException e) {
            String errorMsg = "Can't create " + objectToCreate + " : Invalid direction [" + directionStr + "]";
            throw new InstructionParserException(e, errorMsg, line);
        }
    }

    private void checkLawnPosition(Lawn lawn, Position position, String line, String objectToCreate) throws InstructionParserException {
        if (!lawn.isCellCanBeMowed(position)) {
            throw new InstructionParserException("Can't create " + objectToCreate + " : Invalid position [" + position + "] in lawn", line);
        }
    }

    private int parsePositiveNumber(String stringToParse, String line, String objectToCreate, String argumentName) throws InstructionParserException {
        int parsedNumber = parseNumber(stringToParse, line, objectToCreate, argumentName);

        if (parsedNumber < 0) {
            String errorMsg = "Can't create " + objectToCreate + " : " + argumentName + " can't be negative '" + stringToParse + "'";
            throw new InstructionParserException(errorMsg, line);
        }

        return parsedNumber;
    }

    private int parseNumber(String stringToParse, String line, String objectToCreate, String argumentName) throws InstructionParserException {
        try {
            return Integer.parseInt(stringToParse);
        } catch (NumberFormatException e) {
            String errorMsg = "Can't create " + objectToCreate + " : '" + stringToParse + "' is an invalid number for " + argumentName;
            throw new InstructionParserException(e, errorMsg, line);
        }
    }

    private void checkArgumentCount(StringTokenizer tokenizer, int argumentExpected, String line, String objectToCreate) throws InstructionParserException {
        int argumentCount = tokenizer.countTokens();
        if (argumentCount != argumentExpected) {
            String errorMessage = String.format("Can't create " + objectToCreate + " : " + argumentExpected + " arguments expected, %d found", argumentCount);
            throw new InstructionParserException(errorMessage, line);
        }
    }

    public List<InstructionSet> parseMowerInstructions() throws IOException, InstructionParserException {
        return parseMowerInstructions(scanner.nextLine());
    }

    private List<InstructionSet> parseMowerInstructions(String line) throws InstructionParserException {
        List<InstructionSet> instructions = new ArrayList<>(line.length());

        for (char c : line.toCharArray()) {
            instructions.add(parseInstructionSet(c, "Instruction List", line));
        }
        return instructions;
    }

    private InstructionSet parseInstructionSet(char c, String objectToCreate, String line) throws InstructionParserException {
        InstructionSet instructionSet;
        try {
            instructionSet = InstructionSet.valueOf(String.valueOf(c));
        } catch (IllegalArgumentException e) {
            String errorMsg = "Can't create " + objectToCreate + " : [" + c + "] is invalid instruction";
            throw new InstructionParserException(e, errorMsg, line);
        }
        return instructionSet;
    }

    public boolean hasNextInstruction() {
        return scanner.hasNextLine();
    }

    @Override
    public void close() throws IOException {
        scanner.close();
    }
}
