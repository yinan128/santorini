package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.util.Location;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HermesGameLogicTest {

    private GameLogic hermes;
    private Board board;

    @Before
    public void setup() {
        hermes = new HermesGameLogic(new BasicGameLogic());
        board = new Board();
        board.placeWorker(new Worker(Location.get(0, 0), new Player(0)), Location.get(0, 0));
    }

    @Test
    public void testMoveManyTimes() {
        hermes.move(board, Location.get(0, 0), Location.get(1, 1));
        assertTrue(board.isFieldOccupied(Location.get(1, 1)));
        assertFalse(board.isFieldOccupied(Location.get(0, 0)));

        hermes.move(board, Location.get(1, 1), Location.get(2, 2));
        assertTrue(board.isFieldOccupied(Location.get(2, 2)));
        assertFalse(board.isFieldOccupied(Location.get(1, 1)));

        hermes.move(board, Location.get(2, 2), Location.get(3, 3));
        assertTrue(board.isFieldOccupied(Location.get(3, 3)));
        assertFalse(board.isFieldOccupied(Location.get(2, 2)));

        hermes.move(board, Location.get(3, 3), Location.get(4, 4));
        assertTrue(board.isFieldOccupied(Location.get(4, 4)));
        assertFalse(board.isFieldOccupied(Location.get(3, 3)));
    }

}