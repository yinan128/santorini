package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.util.Location;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasicGameLogicTest {

    private Board board;
    private GameLogic basicGameLogic;

    @Before
    public void setup() {
        basicGameLogic = new BasicGameLogic();
        board = new Board();
        board.placeWorker(new Worker(Location.get(0, 0), new Player(0)), Location.get(0, 0));
        board.placeWorker(new Worker(Location.get(0, 1), new Player(1)), Location.get(0, 1));
    }

    @Test
    public void testValidMove_StartEmpty() {
        assertFalse(basicGameLogic.isValidMove(board, Location.get(1, 1), Location.get(2, 2)));
    }

    @Test
    public void testValidMove_DestOccupied() {
        assertFalse(basicGameLogic.isValidMove(board, Location.get(0, 0), Location.get(0, 1)));
    }

    @Test
    public void testValidMove_DestEmpty() {
        assertTrue(basicGameLogic.isValidMove(board, Location.get(0, 0), Location.get(1, 1)));
    }

    @Test
    public void testValidMove_DestOutOfPerimeter() {
        assertFalse(basicGameLogic.isValidMove(board, Location.get(0, 0), Location.get(2, 2)));
    }

    @Test
    public void testValidMove_DestHasDome() {
        for (int i = 0; i < 4; i++) {
            board.buildOn(Location.get(1, 1));
        }
        assertFalse(basicGameLogic.isValidMove(board, Location.get(0, 0), Location.get(1, 1)));
    }

    @Test
    public void testMove() {
        assertTrue(basicGameLogic.move(board, Location.get(0, 0), Location.get(1, 1)));
        assertTrue(board.isFieldOccupied(Location.get(1, 1)));
    }

    @Test
    public void testIsBuildable_StartEmpty() {
        assertFalse(basicGameLogic.isBuildable(board, Location.get(1, 1), Location.get(2, 2)));
    }

    @Test
    public void testIsBuildable_DestOutOfPerimeter() {
        assertFalse(basicGameLogic.isBuildable(board, Location.get(0, 0), Location.get(2, 2)));
    }

    @Test
    public void testIsBuildable_DestOccupied() {
        assertFalse(basicGameLogic.isBuildable(board, Location.get(0, 0), Location.get(0, 1)));
    }

    @Test
    public void testIsBuildable_DestDomed() {
        for (int i = 0; i < 4; i++) {
            board.buildOn(Location.get(1, 1));
        }
        assertFalse(basicGameLogic.isBuildable(board, Location.get(0, 0), Location.get(1, 1)));
    }

    @Test
    public void testIsBuildable_DestEmpty() {
        assertTrue(basicGameLogic.isBuildable(board, Location.get(0, 0), Location.get(1, 0)));
    }

    @Test
    public void testBuild_OneLevel() {
        assertTrue(basicGameLogic.build(board, Location.get(1, 0)));
        assertEquals(1, board.getField(Location.get(1, 0)).getHeight());
    }

    @Test
    public void testBuild_TwoLevels() {
        for (int i = 0; i < 2; i++) {
            assertTrue(basicGameLogic.build(board, Location.get(1, 0)));
        }
        assertEquals(2, board.getField(Location.get(1, 0)).getHeight());
    }

    @Test
    public void testBuild_ThreeLevels() {
        for (int i = 0; i < 3; i++) {
            assertTrue(basicGameLogic.build(board, Location.get(1, 0)));
        }
        assertEquals(3, board.getField(Location.get(1, 0)).getHeight());
    }

    @Test
    public void testBuild_FourLevels() {
        for (int i = 0; i < 4; i++) {
            assertTrue(basicGameLogic.build(board, Location.get(1, 0)));
        }
        assertEquals(4, board.getField(Location.get(1, 0)).getHeight());
    }


    @Test
    public void testWinningCase_ThreeBuilds() {
        for (int i = 0; i < 3; i++) {
            assertTrue(basicGameLogic.build(board, Location.get(1, 0)));
        }
        assertTrue(basicGameLogic.isWinningCase(board, Location.get(1, 0)));
    }

    @Test
    public void testWinningCase_TwoBuilds() {
        for (int i = 0; i < 2; i++) {
            assertTrue(basicGameLogic.build(board, Location.get(1, 0)));
        }
        assertFalse(basicGameLogic.isWinningCase(board, Location.get(1, 0)));
    }

    @Test
    public void testWinningCase_ZeroBuild() {
        assertFalse(basicGameLogic.isWinningCase(board, Location.get(1, 0)));
    }




}
