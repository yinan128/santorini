package edu.cmu.cs214.hw3.playground;

import java.util.HashMap;
import java.util.Map;

public class Tower {
    private static final int MAX_BLOCKS = 3;
    private int level;
    private final Map<BuildingComponent, Integer> construction;

    public Tower() {
        level = 0;
        construction = new HashMap<>();
        construction.put(BuildingComponent.BLOCK, 0);
        construction.put(BuildingComponent.DOME, 0);
    }

    public boolean build() {
        if (level < MAX_BLOCKS) {
            level++;
            construction.put(BuildingComponent.BLOCK, construction.get(BuildingComponent.BLOCK) + 1);
            return true;
        } else if (level == MAX_BLOCKS) {
            level++;
            construction.put(BuildingComponent.DOME, 1);
            return true;
        } else {
            return false;
        }
    }

    public boolean isBuildable() {
        return construction.get(BuildingComponent.DOME) != 1;
    }

    public int getHeight() {
        return level;
    }

    public boolean isBlockFull() {
        return level == MAX_BLOCKS;
    }
}
