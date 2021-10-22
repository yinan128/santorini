package edu.cmu.cs214.hw3.player;

import edu.cmu.cs214.hw3.position.Direction;
import edu.cmu.cs214.hw3.position.Location;

public class Worker {

    private Location location;
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

    public static boolean fromSamePlayer(Worker workerOnField, Worker worker) {
        return workerOnField.getPlayer() == worker.getPlayer();
    }

    private Player getPlayer() {
        return player;
    }


    public Worker moveTo(Location dest) {
        return new Worker(dest, this);
    }

    public Worker moveBack() {
        if (prevState == null) {
            throw new IllegalStateException();
        }
        return prevState;
    }

    public Location getDestination(Direction dir) {
        return location.getRelativeLoc(dir);
    }

    public Location getLocation() {
        return location;
    }

    public Worker getPrevState() {
        return prevState;
    }

}
