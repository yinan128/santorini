package edu.cmu.cs214.hw3.playground;

import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.util.Location;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A class to represent the field on the game board.
 */
public class Field {

    private Location location;
    private final Tower building;
    private Queue<Worker> workers;

    public Field(Location location) {
        this.location = location;
        building = new Tower();
        workers = new LinkedList<>();
    }

    /**
     * static method to tell if a worker can move from the start to the destination.
     * According to the basic game rule, worker can at most move up for one step.
     * @param start start field.
     * @param dest destination field.
     * @return true if it is feasible to move from start to destination.
     */
    public static boolean isMovable(Field start, Field dest) {
        return dest.getHeight() - start.getHeight() <= 1;
    }

    /**
     * perform a build action on the current field.
     * @return true if build is successful.
     */
    public boolean build() {
        return building.build();
    }

    /**
     * Identify if the current field is occupied by a worker.
     * @return true if the current field is occupied.
     */
    public boolean isOccupied() {
        return workers.size() != 0;
    }

    /**
     * Identify if the current field has a tower with dome.
     * @return true if the current field has a tower with dome.
     */
    public boolean hasDome() {
        return building.hasDome();
    }

    /**
     * Identify if the current field has a tower with its max number of blocks.
     * @return true if the tower on the current field has max blocks.
     */
    public boolean isBlockFull() {
        return building.isBlockFull();
    }

    /**
     * Getter for the height of the building on the current field.
     * @return height of the building
     */
    public int getHeight() {
        return building.getHeight();
    }

    /**
     * Getter for the worker on the current field.
     * @return the worker on the current field.
     */
    public Worker getWorker() {
        return workers.peek();
    }

    /**
     * Method to accept a migrated worker from other field.
     * @param worker the migrated worker.
     */
    public void addMovedWorker(Worker worker){
        workers.add(worker.moveTo(this.location));
    }

    /**
     * Method to remove the worker on the current field.
     * @return the worker to be removed.
     */
    public Worker removeWorker() {
        Worker workerToRemove = workers.poll();
        if (workerToRemove == null) {
            throw new IllegalStateException("No worker to remove.");
        }
        return workerToRemove;
    }

    /**
     * Method to place a new worker onto this field.
     * This method should only be used at the start of the game when players are initiating their worker locations.
     * @param worker the worker to be placed.
     */
    public void setWorker(Worker worker) {
        workers.add(worker);
    }

    /**
     * Getter for the worker owner on this field.
     * @return the player who owns the worker.
     */
    public Player getWorkerOwner() {
        if (getWorker() == null) {
            return null;
        }
        return getWorker().getPlayer();
    }

    /**
     * action to build a dome on the field.
     * @return true if build success.
     */
    public boolean buildDome() {
        return building.buildDome();
    }

    /**
     * action to remove one block from the tower on this field.
     */
    public void removeOneBlock() {
        building.removeOneBlock();
    }
}
