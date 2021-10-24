package edu.cmu.cs214.hw3.playground;

import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.position.Location;

import java.util.LinkedList;
import java.util.Queue;

public class Field {

    private Location location;
    private boolean hasWorker;
    private final Tower building;
    private Worker worker;
    private Queue<Worker> workers;

    public static boolean isMovable(Field start, Field dest) {
        return dest.getHeight() - start.getHeight() <= 1;
    }

    public Field(Location location, boolean hasWorker) {
        this.location = location;
        this.hasWorker = hasWorker;
        building = new Tower();
        workers = new LinkedList<>();
    }

    public Field(Location location) {
        this(location, false);
    }

    public void occupy(Worker worker) {
        this.worker = worker;
    }

    public void free() {
        worker = null;
    }

    public boolean build() {
        if (isOccupied()) {
            return false;
        }
        building.build();
        return true;
    }

    public boolean isOccupied() {
        return workers.size() != 0;
    }

    public boolean hasDome() {
        return !building.isBuildable();
    }

    public boolean isBlockFull() {
        return building.isBlockFull();
    }

    private boolean isBuildable() {
        return building.isBuildable();
    }

    public int getHeight() {
        return building.getHeight();
    }


    public boolean hasWorker() {
        return workers.size() != 0;
    }

    public int getLevel() {
        return building.getHeight();
    }

    public Worker getWorker() {
        return workers.peek();
    }

    public void addMovedWorker(Worker worker){
        workers.add(worker.moveTo(this.location));
    }

    public Worker removeWorker() {
        Worker workerToRemove = workers.poll();
        if (workerToRemove == null) {
            throw new IllegalStateException("No worker to remove.");
        }
        return workerToRemove;
    }

    public void setWorker(Worker worker) {
        workers.add(worker);
    }

    public Player getWorkerOwner() {
        return getWorker().getPlayer();
    }
}
