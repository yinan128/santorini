package edu.cmu.cs214.hw3.playground;

public class Field {

    private boolean hasDome;
    private boolean hasWorker;
    private final Tower building;

    public static boolean isMovable(Field start, Field dest) {
        return !dest.isOccupied() && (dest.getHeight() - start.getHeight() <= 1);
    }

    public Field(boolean hasWorker) {
        hasDome = false;
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
        if (!isOccupied()) {
            return false;
        }
        building.build();
        if (!isBuildable()) {
            // check if it can be built a step higher.
            hasDome = true;
        }
        return true;
    }

    public boolean isOccupied() {
        return hasDome || hasWorker;
    }

    private boolean isBuildable() {
        return building.isBuildable();
    }



    public boolean isBlockFull() {
        return building.isBlockFull();
    }

    private int getHeight() {
        return building.getHeight();
    }

}
