package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.util.Location;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class HephaestusGameLogicTest {

    private GameLogic hephaestus;
    private Board board;


    @Before
    public void setup() {
        hephaestus = new HephaestusGameLogic(new BasicGameLogic());
        board = new Board();
        board.placeWorker(new Worker(Location.get(0, 0), new Player(0)), Location.get(0, 0));
    }


    @Test
    public void testIsBuildable_SecondBuildOnPrevLocation() {
        hephaestus.build(board, Location.get(1, 1));
        assertTrue(hephaestus.isBuildable(board, Location.get(0, 0),Location.get(1, 1)));
    }

    @Test
    public void testIsBuildable_SecondBuildOnAnotherLocation() {
        hephaestus.build(board, Location.get(1, 1));
        assertFalse(hephaestus.isBuildable(board, Location.get(0, 0),Location.get(1, 0)));
    }

}