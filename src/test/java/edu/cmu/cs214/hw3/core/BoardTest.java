package edu.cmu.cs214.hw3.core;

import edu.cmu.cs214.hw3.position.Direction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    private Game board;

    @Before
    public void setup() {
        board = new Game();
        // place workers at the corners.
        board.addStartLocation(0, 0, 0);
        board.addStartLocation(0, 4, 4);
        board.addStartLocation(1, 0, 4);
        board.addStartLocation(1, 4, 0);
    }

    @Test
    public void testMoveWorkerOutOfBoard() {
        board.startRound();
        assertFalse(board.moveWorker(1, Direction.RIGHT));
    }

    @Test
    public void testMoveToOccupied() {
        board.startRound();
        for (int i = 0; i < 3; i++) {
            assertTrue(board.moveWorker(0, Direction.RIGHT));
        }
        // move to an occupied field.
        assertFalse(board.moveWorker(0, Direction.RIGHT));
    }

    @Test
    public void testMoveToOneLvlHigh() {
        board.startRound();
        board.build(0, Direction.RIGHT);
        assertTrue(board.moveWorker(0, Direction.RIGHT));
    }

    @Test
    public void testMoveToTwoLvlHigh() {
        board.startRound();
        for (int i = 0; i < 2; i++) {
            board.build(0, Direction.RIGHT);
        }
        assertFalse(board.moveWorker(0, Direction.RIGHT));
    }

    @Test
    public void testMoveFromOneLvlToTwoLvl() {
        board.startRound();
        for (int i = 0; i < 2; i++) {
            board.build(0, Direction.RIGHT);
        }
        board.build(0, Direction.DOWN);
        assertTrue(board.moveWorker(0, Direction.DOWN));
        assertTrue(board.moveWorker(0, Direction.UPRIGHT));
    }

    @Test
    public void testMoveFromTwoLvlToGround() {
        board.startRound();
        for (int i = 0; i < 2; i++) {
            board.build(0, Direction.RIGHT);
        }
        board.build(0, Direction.DOWN);
        board.moveWorker(0, Direction.DOWN);
        board.moveWorker(0, Direction.UPRIGHT);
        assertTrue(board.moveWorker(0, Direction.RIGHT));
    }

    @Test
    public void testMoveToThreeLvlAndGameOver() {
        board.startRound();
        for (int i = 0; i < 2; i++) {
            board.build(0, Direction.RIGHT);
            board.moveWorker(0, Direction.RIGHT);
            board.build(0, Direction.LEFT);
            board.moveWorker(0, Direction.LEFT);
        }
        //standing on two-floor high
        board.build(0, Direction.RIGHT);
        board.moveWorker(0, Direction.RIGHT);
        assertTrue(board.isGameOver());
    }


    @Test
    public void testBuildOnOccupied() {
        board.startRound();
        for (int i = 0; i < 3; i++) {
            board.moveWorker(0, Direction.RIGHT);
        }
        // build action on an occupied field.
        assertFalse(board.build(0, Direction.RIGHT));
    }

    @Test
    public void testBuildOnDomedBuilding() {
        board.startRound();
        for (int i = 0; i < 4; i++) {
            assertTrue(board.build(0, Direction.RIGHT));
        }
        assertFalse(board.build(0, Direction.RIGHT));
    }

    @Test
    public void testBuildOutOfBoard() {
        board.startRound();
        assertFalse(board.build(0, Direction.UP));
    }



}