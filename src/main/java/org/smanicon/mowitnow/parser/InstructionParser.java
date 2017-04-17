package org.smanicon.mowitnow.parser;

import org.smanicon.mowitnow.models.BoundedLawn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.StringTokenizer;

class InstructionParser {
    private final BufferedReader bufferedReader;

    public static InstructionParser parse(Reader r) throws IOException, InstructionParserException {
        return new InstructionParser(new BufferedReader(r));
    }

    public InstructionParser(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public BoundedLawn parseBoundedLawn() throws IOException, InstructionParserException {
        return parseBoundedLawn(bufferedReader.readLine());
    }

    private BoundedLawn parseBoundedLawn(String s) throws InstructionParserException {
        StringTokenizer tokenizer = new StringTokenizer(s);

        int argumentCount = tokenizer.countTokens();
        if (argumentCount != 2) {
            String errorMessage = String.format("Can't create Lawn : 2 arguments expected, %d found", argumentCount);
            throw new InstructionParserException(errorMessage, s);
        }

        int width = parsePositiveNumber(tokenizer.nextToken(), s, "Can't create Lawn", "width");
        int height = parsePositiveNumber(tokenizer.nextToken(), s, "Can't create Lawn", "height");

        return new BoundedLawn(width, height);
    }

    private int parsePositiveNumber(String stringToParse, String line, String prefixMsg, String argumentName) throws InstructionParserException {
        int parsedNumber = parseNumber(stringToParse, line, prefixMsg, argumentName);

        if (parsedNumber < 0) {
            String errorMsg = prefixMsg + " : " + argumentName + " can't be negative '" + stringToParse + "'";
            throw new InstructionParserException(errorMsg, line);
        }

        return parsedNumber;
    }

    private int parseNumber(String stringToParse, String line, String prefixMsg, String argumentName) throws InstructionParserException {
        try {
            return Integer.parseInt(stringToParse);
        } catch (NumberFormatException e) {
            String errorMsg = prefixMsg + " : '" + stringToParse + "' is an invalid number for " + argumentName;
            throw new InstructionParserException(e, errorMsg, line);
        }
    }
}
