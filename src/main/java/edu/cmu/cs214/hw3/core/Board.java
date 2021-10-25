package edu.cmu.cs214.hw3.core;

import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.playground.Field;
import edu.cmu.cs214.hw3.util.Location;

import java.util.*;

/**
 * The board in the game.
 */
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

    /**
     * Identify if the location is on the board.
     * @param location the to-be-identified location.
     * @return true if the location is on the board.
     */
    public boolean isCoordOnBoard(Location location) {
        return fieldMap.containsKey(location);
    }

    /**
     * Identify if the designated location has a tower with max blocks it can contain.
     * This method is to check if the player can end the game.
     * @param location the designated location
     * @return true if the location has max blocks.
     */
    public boolean isFieldBlockFull(Location location) {
        return fieldMap.get(location).isBlockFull();
    }

    /**
     * Identify if the designated field is occupied by a worker.
     * @param location the location of the designated field.
     * @return true if the field is occupied.
     */
    public boolean isFieldOccupied(Location location) {
        return fieldMap.get(location).isOccupied();
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

    /**
     * Getter of the worker on the designated field.
     * @param location the location of the designated field.
     * @return the worker on the field (could be null but should not happen)
     */
    public Worker getWorkerOnField(Location location) {
        return fieldMap.get(location).getWorker();
    }

    /**
     * Identify if the given location has a tower with dome.
     * @param destination the given location
     * @return true if the location has a tower with dome.
     */
    public boolean isFieldDomed(Location destination) {
        return fieldMap.get(destination).hasDome();
    }

    /**
     * to calculate the height difference between the towers on two locations.
     * @param destination the location of one tower.
     * @param start the location of another tower.
     * @return height difference of two towers.
     */
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

    /**
     * Action to make a build action on the given location.
     * @param location a given location we want to perform a build.
     * @return true if build is successful.
     */
    public boolean buildOn(Location location) {
        return fieldMap.get(location).build();
    }

    /**
     * Method to place the worker onto the given location.
     * This method should only be used at the start of the game to set the worker locations.
     * @param worker the worker to be placed.
     * @param location the location for the worker.
     * @return true if placement is successful.
     */
    public boolean placeWorker(Worker worker, Location location) {
        if (fieldMap.get(location).isOccupied()) return false;
        fieldMap.get(location).setWorker(worker);
        return true;
    }


    /**
     * Getter for the owner of the player on the given location.
     * @param start the given location contain a worker.
     * @return the owner of the worker.
     */
    public Player getWorkerOwner(Location start) {
        return fieldMap.get(start).getWorkerOwner();
    }

    /**
     * calculate the level change of the worker at the given location from his last movement.
     * @param location the location of the worker
     * @return level change, positive if worker moved up.
     */
    public int workerLevelChange(Location location) {
        return deltaHeight(location, getWorkerOnField(location).getPrevState().getLocation());
    }

    /**
     * action to build a dome on the given location disregarding its number of blocks.
     * @param location the given location we want to build a dome.
     * @return true if build is successful.
     */
    public boolean buildDome(Location location) {
        return fieldMap.get(location).buildDome();
    }

    /**
     * to remove one block from the given location.
     * @param location the given location we want to remove one block from.
     */
    public void removeOneBlock(Location location) {
        fieldMap.get(location).removeOneBlock();
    }
}
