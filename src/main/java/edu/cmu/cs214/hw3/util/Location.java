package edu.cmu.cs214.hw3.util;

import java.util.*;

/**
 * A class to represent the location (row, col) on the board.
 */
public final class Location {

    private static final Map<Integer, Location> cache = new HashMap<>();
    private final int row;
    private final int col;

    private Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // static factory.
    public static Location get(int row, int col) {
        int hashCode = Objects.hash(row, col);
        if (!cache.containsKey(hashCode)) {
            Location result = new Location(row, col);
            cache.put(hashCode, result);
            return result;
        }
        return cache.get(hashCode);
    }

    /**
     * Method to get the locations which are on the perimeter of the current location.
     * @return a list of locations.
     */
    public List<Location> getPerimeter() {
        List<Location> result = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                // exclude itself.
                if (i == 0 && j == 0) {
                    continue;
                }
                result.add(Location.get(row + i, col + j));
            }
        }
        return result;
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
