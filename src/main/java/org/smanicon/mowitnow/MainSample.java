package org.smanicon.mowitnow;

import org.smanicon.mowitnow.models.LawnMowerView;
import org.smanicon.mowitnow.parser.InstructionParserException;
import org.smanicon.mowitnow.parser.LawnMowerProgramRunner;

import java.io.*;

public class MainSample {
    public static void main(String...argv) {
        try {
            if(argv.length > 2) {
                System.err.println(argv[0] + " [filepath]");
                System.exit(1);
            }

            Reader programReader = (argv.length == 2)
                    ? new FileReader(argv[1])
                    : new InputStreamReader(System.in);

            LawnMowerProgramRunner programRunner = new LawnMowerProgramRunner(programReader);
            programRunner.run();
            programRunner.getLawnMower()
                    .stream()
                    .map(MainSample::lawnMowerViewToString)
                    .forEach(System.out::println);

        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(2);
        } catch (InstructionParserException e) {
            System.err.println(e.getMessage());
            System.exit(3);
        }
    }

    private static String lawnMowerViewToString(LawnMowerView x) {
        StringBuilder s = new StringBuilder();
        s.append(x.getPosition().getX());
        s.append(" ");
        s.append(x.getPosition().getY());
        s.append(" ");
        s.append(x.getDirection().name().charAt(0));
        return s.toString();
    }
}
