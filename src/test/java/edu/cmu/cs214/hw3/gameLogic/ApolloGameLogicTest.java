package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.util.Location;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class ApolloGameLogicTest {

    private GameLogic apollo;
    private Board board;
    private Player playerA;
    private Player playerB;

    @Before
    public void setup() {
        apollo = new ApolloGameLogic(new BasicGameLogic());
        board = new Board();
        playerA = new Player(0);
        playerB = new Player(1);
        board.placeWorker(new Worker(Location.get(0, 0), playerA), Location.get(0, 0));
        board.placeWorker(new Worker(Location.get(4, 4), playerA), Location.get(4, 4));
        board.placeWorker(new Worker(Location.get(1, 1), playerB), Location.get(1, 1));
    }

    @Test
    public void testIsValidMove_Unoccupied_OffBoard() {
        assertFalse(apollo.isValidMove(board, Location.get(0, 0), Location.get(5, 5)));
    }

    @Test
    public void testIsValidMove_Unoccupied_Domed() {
        for (int i = 0; i < 4; i++) {
            board.buildOn(Location.get(0, 1));
        }
        assertFalse(apollo.isValidMove(board, Location.get(0, 0), Location.get(0, 1)));
    }

    @Test
    public void testIsValidMove_UnOccupied_NotReachable() {
        for (int i = 0; i < 2; i++) {
            board.buildOn(Location.get(4, 3));
        }
        assertFalse(apollo.isValidMove(board, Location.get(4, 4), Location.get(4, 3)));
    }

    @Test
    public void testIsValidMove_occupied_SameOwner() {
        assertFalse(apollo.isValidMove(board, Location.get(0, 0), Location.get(4, 4)));
    }

    @Test
    public void testIsValidMove_occupiedByOpponentWorker() {
        assertTrue(apollo.isValidMove(board, Location.get(0, 0), Location.get(1, 1)));
    }

    @Test
    public void testMove_Unoccupied() {
        assertTrue(board.isFieldOccupied(Location.get(0, 0)));
        apollo.move(board, Location.get(0, 0), Location.get(1, 0));
        assertTrue(board.isFieldOccupied(Location.get(1, 0)));
        assertFalse(board.isFieldOccupied(Location.get(0, 0)));
    }

    @Test
    public void testMove_Occupied() {
        assertEquals(playerA, board.getWorkerOwner(Location.get(0, 0)));
        assertEquals(playerB, board.getWorkerOwner(Location.get(1, 1)));
        apollo.move(board, Location.get(0, 0), Location.get(1, 1));
        assertEquals(playerA, board.getWorkerOwner(Location.get(1, 1)));
        assertEquals(playerB, board.getWorkerOwner(Location.get(0, 0)));
    }



}
