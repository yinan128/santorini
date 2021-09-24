package edu.cmu.cs214.hw3.player;
import edu.cmu.cs214.hw3.position.Location;
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
    public void testAddWorkerInSameLocation() {
        player.addWorker(new Location(1,1));
        assertFalse(player.addWorker(new Location(1, 1)));
    }

    @Test
    public void testAddWorkerInDifferentLocation() {
        player.addWorker(new Location(1,1));
        assertTrue(player.addWorker(new Location(0, 0)));
    }

    @Test
    public void testAddWorkerExceedLimit() {
        player.addWorker(new Location(1,1));
        player.addWorker(new Location(0,0));
        assertFalse(player.addWorker(new Location(1, 0)));
    }


}