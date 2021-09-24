package edu.cmu.cs214.hw3.playground;

public class Field {

    private boolean hasWorker;
    private final Tower building;

    public static boolean isMovable(Field start, Field dest) {
        return !dest.isOccupied() && (dest.getHeight() - start.getHeight() <= 1);
    }

    public Field(boolean hasWorker) {
        this.hasWorker = hasWorker;
        building = new Tower();
    }

    public Field() {
        this(false);
    }

    public void occupy() {
        hasWorker = true;
    }

    public void free() {
        hasWorker = false;
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
        return hasWorker;
    }

    public int getLevel() {
        return building.getHeight();
    }
}
