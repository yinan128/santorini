package edu.cmu.cs214.hw3.playground;

import edu.cmu.cs214.hw3.player.Worker;

public class Field {

    private boolean hasWorker;
    private final Tower building;
    private Worker worker;

    public static boolean isMovable(Field start, Field dest) {
        return dest.getHeight() - start.getHeight() <= 1;
    }

    public Field(boolean hasWorker) {
        this.hasWorker = hasWorker;
        building = new Tower();
    }

    public Field() {
        this(false);
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
        return !isBuildable() || hasWorker;
    }

    public boolean isBlockFull() {
        return building.isBlockFull();
    }

    private boolean isBuildable() {
        return building.isBuildable();
    }

    private int getHeight() {
        return building.getHeight();
    }

    public boolean hasWorker() {
        return worker != null;
    }

    public int getLevel() {
        return building.getHeight();
    }

    public Worker getWorker() {
        if (worker == null) {
            throw new IllegalStateException();
        }
        return worker;
    }
}
