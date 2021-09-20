package edu.cmu.cs214.hw3;

import edu.cmu.cs214.hw3.position.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * Convert user input into parameters.
 */
public final class Parser {

    public static List<Object> parse(String input) {
        List<Object> parameters = new ArrayList<>();
        if (input.contains(",")) {
            parameters.add(Integer.parseInt(input.substring(0, input.indexOf(",")).trim()));
            String secondPara = input.substring(input.indexOf(",") + 1).trim();
            for (Direction dir: Direction.values()) {
                if (secondPara.compareToIgnoreCase(dir.toString()) == 0) {
                    parameters.add(dir);
                }
            }
        }
        return parameters;
    }



}
