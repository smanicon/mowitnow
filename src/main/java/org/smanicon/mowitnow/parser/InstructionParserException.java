package org.smanicon.mowitnow.parser;

public class InstructionParserException extends Exception {
    private final String line;

    public InstructionParserException(String msg, String line) {
        this(null, msg, line);
    }

    public InstructionParserException(Exception e, String msg, String line) {
        super(msg + ((line == null) ? "" : " - at line [" + line + "]"), e);
        this.line = line;
    }

    public String getLine() {
        return line;
    }
}
