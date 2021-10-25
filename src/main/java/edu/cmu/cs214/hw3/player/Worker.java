package edu.cmu.cs214.hw3.player;

import edu.cmu.cs214.hw3.util.Location;

public class Worker {

    private Location location;

    /**
     * the previous state of the worker.
     */
    private Worker prevState;

    private Player player;

    public Worker(Location location, Player player) {
        this.location = location;
        prevState = null;
        this.player = player;
    }

    public Worker(Location location, Worker prevState) {
        this.location = location;
        this.prevState = prevState;
        this.player = prevState.player;
    }

    /**
     * static method to identify if the given two workers belong to the same player.
     * @param workerOnField worker A
     * @param worker worker B
     * @return true if worker A and worker B belong to the same player.
     */
    public static boolean fromSamePlayer(Worker workerOnField, Worker worker) {
        return workerOnField.getPlayer() == worker.getPlayer();
    }

    /**
     * Getter for the owner.
     * @return owner of this instance.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * action to make the worker move to the destination.
     * @param dest destination.
     * @return a worker instance (its future state) after the movement.
     */
    public Worker moveTo(Location dest) {
        return new Worker(dest, this);
    }

    public Location getLocation() {
        return location;
    }

    public Worker getPrevState() {
        return prevState;
    }

}
