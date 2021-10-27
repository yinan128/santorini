package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.util.Location;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BlockedGameLogicTest {

    private GameLogic blocked;
    private GameLogic basic;
    private Board board;

    @Before
    public void setup() {
        basic = new BasicGameLogic();
        blocked = new BlockedGameLogic(basic);
        board = new Board();
        board.placeWorker(new Worker(Location.get(0, 0), new Player(0)), Location.get(0, 0));
    }

    @Test
    public void testIsValidMove() {
        basic.build(board, Location.get(1, 1));
        assertFalse(blocked.isValidMove(board, Location.get(0, 0), Location.get(1, 1)));
    }

}