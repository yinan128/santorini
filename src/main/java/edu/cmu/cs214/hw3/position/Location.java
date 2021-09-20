package edu.cmu.cs214.hw3.position;

public final class Location {

    private final int row;
    private final int col;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Location getRelativeLoc(Direction dir) {
        if (dir == null) {
            throw new IllegalArgumentException("invalid direction");
        }
        int destRow = row;
        int destCol = col;
        switch (dir) {
            case UP -> destRow = destRow - 1;
            case UPLEFT -> {
                destRow = destRow - 1;
                destCol = destCol - 1;
            }
            case UPRIGHT -> {
                destRow = destRow - 1;
                destCol = destCol + 1;
            }
            case LEFT -> destCol = destCol - 1;
            case RIGHT -> destCol = destCol + 1;
            case DOWNLEFT -> {
                destRow = destRow + 1;
                destCol = destCol - 1;
            }
            case DOWN -> destRow = destRow + 1;
            case DOWNRIGHT -> {
                destRow = destRow + 1;
                destCol = destCol + 1;
            }
        }
        return new Location(destRow, destCol);
    }

    @Override
    public String toString() {
        return "Location[" + row + ", " + col + "]";
    }

}
