package edu.cmu.cs214.hw3.position;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Location {

    private static Map<Integer, Location> cache;
    private final int row;
    private final int col;

    private Location(int row, int col) {
        cache = new HashMap<>();
        this.row = row;
        this.col = col;
    }

    public static Location get(int row, int col) {
        int hashCode = Objects.hash(row, col);
        if (!cache.containsKey(hashCode)) {
            Location result = new Location(row, col);
            cache.put(hashCode, result);
            return result;
        }
        return cache.get(hashCode);
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
