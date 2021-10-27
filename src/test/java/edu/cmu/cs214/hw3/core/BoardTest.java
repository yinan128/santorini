package edu.cmu.cs214.hw3.core;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.util.Location;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class BoardTest {

    private Board board;
    private Player playerA;
    private Player playerB;
    private Worker workerA0;
    private Worker workerA1;
    private Worker workerB0;
    private Worker workerB1;

    @Before
    public void setup() {
        board = new Board();
        playerA = new Player(0);
        playerB = new Player(1);
        workerA0 = new Worker(Location.get(0, 0), playerA);
        workerA1 = new Worker(Location.get(1, 1), playerA);
        workerB0 = new Worker(Location.get(1, 0), playerB);
        workerB1 = new Worker(Location.get(0, 1), playerB);
        board.placeWorker(workerA0, Location.get(0, 0));
        board.placeWorker(workerA1, Location.get(1, 1));
        board.placeWorker(workerB0, Location.get(1, 0));
        board.placeWorker(workerB1, Location.get(0, 1));
    }

    @Test
    public void testIsOccupied_WorkerOnField() {
        assertTrue(board.isFieldOccupied(Location.get(0, 0)));
        assertTrue(board.isFieldOccupied(Location.get(1, 1)));
        assertTrue(board.isFieldOccupied(Location.get(0, 1)));
        assertTrue(board.isFieldOccupied(Location.get(1, 0)));
    }

    @Test
    public void testIsOccupied_EmptyField() {
        assertFalse(board.isFieldOccupied(Location.get(2, 2)));
    }

    @Test
    public void testMoveWorker() {
        // at first, destination is empty
        assertFalse(board.isFieldOccupied(Location.get(2, 2)));
        // move worker
        board.moveWorker(Location.get(0, 0), Location.get(2, 2));
        // the start is empty, the destination is occupied.
        assertFalse(board.isFieldOccupied(Location.get(0, 0)));
        assertTrue(board.isFieldOccupied(Location.get(2, 2)));
    }

    @Test
    public void testGetWorker_PlacedWorker() {
        assertEquals(workerA0, board.getWorkerOnField(Location.get(0, 0)));
        assertEquals(workerA1, board.getWorkerOnField(Location.get(1, 1)));
        assertEquals(workerB0, board.getWorkerOnField(Location.get(1, 0)));
        assertEquals(workerB1, board.getWorkerOnField(Location.get(0, 1)));

    }

    @Test
    public void testGetWorker_Null() {
        assertNull(board.getWorkerOnField(Location.get(4, 4)));
    }

    @Test
    public void testGetWorkerOwner() {
        assertEquals(playerA ,board.getWorkerOwner(Location.get(0, 0)));
        assertEquals(playerA ,board.getWorkerOwner(Location.get(1, 1)));
        assertEquals(playerB ,board.getWorkerOwner(Location.get(1, 0)));
        assertEquals(playerB ,board.getWorkerOwner(Location.get(0, 1)));
    }

    @Test
    public void testGetLocationOnPerimeter_AllPerimetersOnBoard() {
        assertEquals(8, board.getLocationPerimeter(Location.get(1, 1)).size());
    }

    @Test
    public void testGetLocationOnPerimeter_SomeNotOnBoard_1() {
        assertEquals(5, board.getLocationPerimeter(Location.get(1, 0)).size());
    }

    @Test
    public void testGetLocationOnPerimeter_SomeNotOnBoard_2() {
        assertEquals(3, board.getLocationPerimeter(Location.get(0, 0)).size());
    }

    @Test
    public void testDeltaHeight_Zero() {
        assertEquals(0, board.deltaHeight(Location.get(0, 0), Location.get(0, 1)));
    }


    @Test
    public void testDeltaHeight_One() {
        board.buildOn(Location.get(0, 0));
        assertEquals(1, board.deltaHeight(Location.get(0, 0), Location.get(0, 1)));
    }


    @Test
    public void testWorkerLevelChange_UpByOne() {
        board.buildOn(Location.get(2, 2));
        board.moveWorker(Location.get(1, 1), Location.get(2, 2));
        assertEquals(1, board.workerLevelChange(Location.get(2, 2)));
    }

    @Test
    public void testWorkerLevelChange_FlatMove() {
        board.moveWorker(Location.get(1, 1), Location.get(2, 2));
        assertEquals(0, board.workerLevelChange(Location.get(2, 2)));
    }

    @Test
    public void testWorkerLevelChange_DownByOne() {
        board.buildOn(Location.get(2, 2));
        board.moveWorker(Location.get(1, 1), Location.get(2, 2));
        board.moveWorker(Location.get(2, 2), Location.get(1, 1));
        assertEquals(-1, board.workerLevelChange(Location.get(1, 1)));
    }

    @Test
    public void testWorkerLevelChange_DownByTwo() {
        board.buildOn(Location.get(2, 2));
        board.buildOn(Location.get(3, 3));
        board.buildOn(Location.get(3, 3));
        board.moveWorker(Location.get(1, 1), Location.get(2, 2));
        board.moveWorker(Location.get(2, 2), Location.get(3, 3));
        board.moveWorker(Location.get(3, 3), Location.get(4, 4));
        assertEquals(-2, board.workerLevelChange(Location.get(4, 4)));
    }

}