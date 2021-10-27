package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.util.Location;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArtemisGameLogicTest {

    private GameLogic artemis;
    private Board board;

    @Before
    public void setArtemis() {
        artemis = new ArtemisGameLogic(new BasicGameLogic());
        board = new Board();
        board.placeWorker(new Worker(Location.get(0, 0), new Player(0)), Location.get(0, 0));
    }

    @Test
    public void testIsValidMove_FirstMove() {
        assertTrue(artemis.isValidMove(board, Location.get(0, 0), Location.get(1, 1)));
    }

    @Test
    public void testMove_FirstMove() {
        assertFalse(board.isFieldOccupied(Location.get(1, 1)));
        artemis.move(board, Location.get(0, 0), Location.get(1, 1));
        assertTrue(board.isFieldOccupied(Location.get(1, 1)));
    }

    @Test
    public void testMove_SecondMove() {
        artemis.move(board, Location.get(0, 0), Location.get(1, 1));
        // second move.
        assertFalse(board.isFieldOccupied(Location.get(2, 2)));
        artemis.move(board, Location.get(1, 1), Location.get(2, 2));
        assertFalse(board.isFieldOccupied(Location.get(1, 1)));
        assertTrue(board.isFieldOccupied(Location.get(2, 2)));
    }


    @Test
    public void testIsValidMove_SecondMove_MoveBack() {
        artemis.move(board, Location.get(0, 0), Location.get(1, 1));
        assertFalse(artemis.isValidMove(board, Location.get(1, 1), Location.get(0, 0)));
    }

    @Test
    public void testIsValidMove_SecondMove_NotMoveBack() {
        artemis.move(board, Location.get(0, 0), Location.get(1, 1));
        assertTrue(artemis.isValidMove(board, Location.get(1, 1), Location.get(2, 2)));
    }

}
