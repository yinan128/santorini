package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.util.Location;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DemeterGameLogicTest {

    private GameLogic demeter;
    private Board board;


    @Before
    public void setup() {
        demeter = new DemeterGameLogic(new BasicGameLogic());
        board = new Board();
        board.placeWorker(new Worker(Location.get(0, 0), new Player(0)), Location.get(0, 0));
    }


    @Test
    public void testIsBuildable_SecondBuildOnPrevLocation() {
        demeter.build(board, Location.get(1, 1));
        assertFalse(demeter.isBuildable(board, Location.get(0, 0),Location.get(1, 1)));
    }

    @Test
    public void testIsBuildable_SecondBuildOnAnotherLocation() {
        demeter.build(board, Location.get(1, 1));
        assertTrue(demeter.isBuildable(board, Location.get(0, 0),Location.get(1, 0)));
    }

}