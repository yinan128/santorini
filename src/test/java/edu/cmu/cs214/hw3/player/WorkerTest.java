package edu.cmu.cs214.hw3.player;

import edu.cmu.cs214.hw3.position.Direction;
import edu.cmu.cs214.hw3.position.Location;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class WorkerTest {

    private Worker worker;

    @Before
    public void setup() {
        worker = new Worker(new Location(5, 5));
    }

    @Test
    public void testMoveUp() {
        worker.moveAtDir(Direction.UP);
        assertEquals(new Location(4, 5), worker.getLocation());
    }

    @Test
    public void testMoveUpLeft() {
        worker.moveAtDir(Direction.UPLEFT);
        assertEquals(new Location(4, 4), worker.getLocation());
    }

    @Test
    public void testMoveDownRight() {
        worker.moveAtDir(Direction.DOWNRIGHT);
        assertEquals(new Location(6, 6), worker.getLocation());
    }

    @Test
    public void testDestinationUp() {
        assertEquals(new Location(4, 5), worker.getDestination(Direction.UP));
        // shouldn't change the worker's location
        assertEquals(new Location(5, 5), worker.getLocation());
    }

    @Test
    public void testDestinationDownLeft() {
        assertEquals(new Location(6, 4), worker.getDestination(Direction.DOWNLEFT));
        // shouldn't change the worker's location
        assertEquals(new Location(5, 5), worker.getLocation());
    }


}