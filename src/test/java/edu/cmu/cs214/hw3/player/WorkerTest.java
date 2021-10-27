package edu.cmu.cs214.hw3.player;

import edu.cmu.cs214.hw3.util.Location;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorkerTest {

    private Worker worker;
    private Player player;

    @Before
    public void setup() {
        player = new Player(0);
        worker = new Worker(Location.get(0, 0), player);
    }

    @Test
    public void testSamePlayer() {
        Worker anotherWorker = new Worker(Location.get(1, 1), player);
        assertTrue(Worker.fromSamePlayer(worker, anotherWorker));
    }

    @Test
    public void testDifferentPlayer() {
        Worker anotherWorker = new Worker(Location.get(1, 1), new Player(1));
        assertFalse(Worker.fromSamePlayer(worker, anotherWorker));
    }

    @Test
    public void testNextState() {
        Worker nextState = worker.moveTo(Location.get(1, 1));
        assertEquals(Location.get(1, 1), nextState.getLocation());
    }

    @Test
    public void testPrevState() {
        Worker nextState = worker.moveTo(Location.get(1, 1));
        assertEquals(Location.get(0, 0), nextState.getPrevState().getLocation());
    }





}