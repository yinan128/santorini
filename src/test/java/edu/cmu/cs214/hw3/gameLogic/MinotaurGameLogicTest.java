package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.util.Location;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MinotaurGameLogicTest {

    private GameLogic minotaur;
    private GameLogic basic;
    private Board board;
    private Player playerA;
    private Player playerB;
    private Worker workerA;
    private Worker workerB;

    @Before
    public void setup() {
        basic = new BasicGameLogic();
        minotaur = new MinotaurGameLogic(basic);
        board = new Board();
        playerA = new Player(0);
        playerB = new Player(1);
        workerA = new Worker(Location.get(0, 0), playerA);
        workerB = new Worker(Location.get(1, 1), playerB);
        board.placeWorker(workerA, Location.get(0, 0));
        board.placeWorker(workerB, Location.get(1, 1));
    }

    @Test
    public void testIsValidMove_Unoccupied_OffBoard() {
        assertFalse(minotaur.isValidMove(board, Location.get(0, 0), Location.get(5, 5)));
    }

    @Test
    public void testIsValidMove_Unoccupied_Domed() {
        for (int i = 0; i < 4; i++) {
            board.buildOn(Location.get(0, 1));
        }
        assertFalse(minotaur.isValidMove(board, Location.get(0, 0), Location.get(0, 1)));
    }

    @Test
    public void testIsValidMove_UnOccupied_NotReachable() {
        for (int i = 0; i < 2; i++) {
            board.buildOn(Location.get(4, 3));
        }
        assertFalse(minotaur.isValidMove(board, Location.get(4, 4), Location.get(4, 3)));
    }

    @Test
    public void testIsValidMove_occupied_SameOwner() {
        assertFalse(minotaur.isValidMove(board, Location.get(0, 0), Location.get(1, 1)));
    }

    @Test
    public void testIsValidMove_Occupied_Opponent_CanMoveBack() {
        basic.move(board, Location.get(1, 1), Location.get(2, 2));
        assertTrue(minotaur.isValidMove(board, Location.get(0, 0), Location.get(2, 2)));
    }

    @Test
    public void testIsValidMove_Occupied_Opponent_CannotMoveBack() {
        assertFalse(minotaur.isValidMove(board, Location.get(0, 0), Location.get(1, 1)));
    }

    @Test
    public void testMove_ForceOpponentWorkerMoveBack() {
        basic.move(board, Location.get(1, 1), Location.get(2, 2));
        minotaur.move(board, Location.get(0, 0), Location.get(2, 2));
        assertEquals(playerA, board.getWorkerOwner(Location.get(2, 2)));
        assertEquals(playerB, board.getWorkerOwner(Location.get(1, 1)));
    }

}