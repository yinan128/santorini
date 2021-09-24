package edu.cmu.cs214.hw3.position;

import java.util.Objects;

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
        switch (dir) {
            case UP -> {
                return new Location(row - 1, col);
            }

            case UPLEFT -> {
                return new Location(row - 1, col - 1);
            }
            case UPRIGHT -> {
                return new Location(row - 1, col + 1);
            }
            case LEFT -> {
                return new Location(row, col - 1);
            }
            case RIGHT -> {
                return new Location(row, col + 1);
            }
            case DOWNLEFT -> {
                return new Location(row + 1, col - 1);
            }
            case DOWN -> {
                return new Location(row + 1, col);
            }
            case DOWNRIGHT -> {
                return new Location(row + 1, col + 1);
            }
            default -> {
                throw new IllegalArgumentException("Direction is not valid,");
            }
        }
    }

    @Override
    public String toString() {
        return "Location[" + row + ", " + col + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return row == location.row && col == location.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
