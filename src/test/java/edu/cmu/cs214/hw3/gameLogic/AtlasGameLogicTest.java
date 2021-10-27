package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.core.Game;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.util.Location;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AtlasGameLogicTest {

    private GameLogic atlas;
    private GameLogic basic;
    private Board board;


    @Before
    public void setup() {
        basic = new BasicGameLogic();
        atlas = new AtlasGameLogic(basic);
        board = new Board();
        board.placeWorker(new Worker(Location.get(0, 0), new Player(0)), Location.get(0, 0));
    }


    @Test
    public void testIsBuildable_SecondBuildOnPrevLocation() {
        atlas.build(board, Location.get(1, 1));
        assertTrue(atlas.isBuildable(board, Location.get(0, 0),Location.get(1, 1)));
    }

    @Test
    public void testIsBuildable_SecondBuildOnAnotherLocation() {
        atlas.build(board, Location.get(1, 1));
        assertFalse(atlas.isBuildable(board, Location.get(0, 0),Location.get(1, 0)));
    }

    @Test
    public void testBuild_BuildOnEmptyField() {
        atlas.build(board, Location.get(1, 1));
        assertFalse(board.isFieldDomed(Location.get(1, 1)));
        atlas.build(board, Location.get(1, 1));
        assertTrue(board.isFieldDomed(Location.get(1, 1)));
    }

    @Test
    public void testBuild_BuildOnOneLevelField() {
        basic.build(board, Location.get(1, 1));
        atlas.build(board, Location.get(1, 1));
        assertFalse(board.isFieldDomed(Location.get(1, 1)));
        atlas.build(board, Location.get(1, 1));
        assertTrue(board.isFieldDomed(Location.get(1, 1)));
    }

}