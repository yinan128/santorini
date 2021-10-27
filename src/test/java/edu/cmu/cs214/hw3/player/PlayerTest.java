package edu.cmu.cs214.hw3.player;


import edu.cmu.cs214.hw3.util.Location;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;

    @Before
    public void setup() {
        player = new Player(0);
    }

    @Test
    public void testAddOneWorker() {
        player.addWorker();
        assertFalse(player.workerFull());
    }

    @Test
    public void testAddTwoWorker() {
        player.addWorker();
        player.addWorker();
        assertTrue(player.workerFull());
    }

    @Test
    public void testAddThreeWorker() {
        player.addWorker();
        player.addWorker();
        assertThrows(IllegalStateException.class, () -> {
            player.addWorker();
        });
    }



}