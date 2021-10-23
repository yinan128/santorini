package edu.cmu.cs214.hw3.core;

import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.playground.Field;
import edu.cmu.cs214.hw3.position.Location;

import java.util.*;

public class Board {

    private static final int COLS = 5;
    private static final int ROWS = 5;

    private final Map<Location, Field> fieldMap;


    public Board() {
        this.fieldMap = new HashMap<>();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                fieldMap.put(Location.get(i, j), new Field(Location.get(i, j)));
            }
        }
    }

    public Field getField(Location location) {
        return fieldMap.get(location);
    }

    public boolean isCoordOnBoard(Location location) {
        return fieldMap.containsKey(location);
    }

    public boolean isFieldBlockFull(Location location) {
        return fieldMap.get(location).isBlockFull();
    }

    public boolean isFieldOccupied(Location destination) {
        return fieldMap.get(destination).isOccupied();
    }


    /**
     * Move the worker from the start Field to the destination Field
     * @param start start location of the field
     * @param destination destination location of the field
     */
    public void moveWorker(Location start, Location destination) {
        Field startField = fieldMap.get(start);
        Field destField = fieldMap.get(destination);
        destField.addMovedWorker(startField.removeWorker());
    }

    public Worker getWorkerOnField(Location location) {
        return fieldMap.get(location).getWorker();
    }

    public boolean isFieldDomed(Location destination) {
        return fieldMap.get(destination).hasDome();
    }

    public int deltaHeight(Location destination, Location start) {
        return fieldMap.get(destination).getHeight() - fieldMap.get(start).getHeight();
    }


    /**
     * return the surrounding onboard locations from the given location.
     * @param location the given location from which you want to find its surroundings.
     * @return the surrounding onboard locations.
     */
    public List<Location> getLocationPerimeter(Location location) {
        List<Location> nearbyLocations = location.getPerimeter();
        List<Location> result = new ArrayList<>();
        for (Location loc : nearbyLocations) {
            if (!isCoordOnBoard(loc)) {
                continue;
            }
            result.add(loc);
        }
        return result;
    }

    public boolean buildOn(Location location) {
        return fieldMap.get(location).build();
    }

    public boolean placeWorker(Worker worker, Location location) {
        if (fieldMap.get(location).isOccupied()) return false;
        fieldMap.get(location).setWorker(worker);
        return true;
    }
}
