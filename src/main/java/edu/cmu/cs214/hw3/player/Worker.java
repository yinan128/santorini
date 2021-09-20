package edu.cmu.cs214.hw3.player;

import edu.cmu.cs214.hw3.position.Direction;
import edu.cmu.cs214.hw3.position.Location;

public class Worker {

    private Location location;

    public Worker(Location location) {
        this.location = location;
    }


    public boolean moveAtDir(Direction dir) {
        location = location.getRelativeLoc(dir);
        return true;
    }

    public Location getDestination(Direction dir) {
        return location.getRelativeLoc(dir);
    }

    public Location getLocation() {
        return location;
    }
}
