package edu.cmu.cs214.hw3.player;

import edu.cmu.cs214.hw3.position.Direction;
import edu.cmu.cs214.hw3.position.Location;

public class Worker {

    private Location location;
    private Worker prevState;

    public Worker(Location location) {
        this.location = location;
        prevState = null;
    }

    public Worker(Location location, Worker prevState) {
        this.location = location;
        this.prevState = prevState;
    }


    public boolean moveTo(Location dest) {
        location = dest;
        return true;
    }

    public Worker moveToNew(Location dest) {
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
}
