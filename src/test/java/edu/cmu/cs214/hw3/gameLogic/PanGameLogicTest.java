package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.util.Location;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PanGameLogicTest {

    private GameLogic pan;
    private Board board;

    @Before
    public void setup() {
        pan = new PanGameLogic(new BasicGameLogic());
        board = new Board();
        board.placeWorker(new Worker(Location.get(0, 0), new Player(0)), Location.get(0, 0));
    }

    @Test
    public void testWin_DownFromTwoFloor() {
        for (int i = 0; i < 2; i++) {
            board.buildOn(Location.get(0, 0));
        }
        pan.move(board, Location.get(0, 0), Location.get(1, 1));
        assertTrue(pan.isWinningCase(board, Location.get(1, 1)));
    }

}