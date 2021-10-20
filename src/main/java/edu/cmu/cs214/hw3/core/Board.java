package edu.cmu.cs214.hw3.core;

import edu.cmu.cs214.hw3.playground.Field;
import edu.cmu.cs214.hw3.position.Location;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private static final int COLS = 5;
    private static final int ROWS = 5;

    private final Map<Location, Field> fieldMap;


    public Board() {
        this.fieldMap = new HashMap<>();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                fieldMap.put(new Location(i, j), new Field());
            }
        }
    }

    public Field getField(Location location) {
        return fieldMap.get(location);
    }

    public boolean isCoordOnBoard(Location location) {
        return fieldMap.containsKey(location);
    }
}
