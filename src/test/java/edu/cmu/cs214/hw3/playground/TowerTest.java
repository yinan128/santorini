package edu.cmu.cs214.hw3.playground;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TowerTest {

    private Tower tower;

    @Before
    public void setup() {
        tower = new Tower();
    }

    @Test
    public void testBuildOneLevel() {
        assertTrue(tower.build());
        assertEquals(1, tower.getHeight());
        assertTrue(tower.isBuildable());
    }

    @Test
    public void testBuildTwoLevels() {
        for (int i = 0; i < 2; i++) {
            tower.build();
        }
        assertEquals(2, tower.getHeight());
        assertTrue(tower.isBuildable());
    }

    @Test
    public void testBuildThreeLevels() {
        for (int i = 0; i < 3; i++) {
            tower.build();
        }
        assertEquals(3, tower.getHeight());
        assertTrue(tower.isBuildable());
        assertTrue(tower.isBlockFull());
    }

    @Test
    public void testBuildFourLevels() {
        for (int i = 0; i < 4; i++) {
            tower.build();
        }
        assertEquals(4, tower.getHeight());
        // no longer buildable
        assertFalse(tower.isBuildable());
    }

    @Test
    public void testBuildFiveLevels() {
        for (int i = 0; i < 4; i++) {
            tower.build();
        }
        assertFalse(tower.build());
        // one more build action won't change the height of tower.
        assertEquals(4, tower.getHeight());
    }
}