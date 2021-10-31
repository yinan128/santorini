package edu.cmu.cs214.hw3.core;

import edu.cmu.cs214.hw3.gameLogic.*;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.util.Location;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    private Game game;
    private Player playerA;
    private Player playerB;


    @Before
    public void setup() {
        game = new Game();
        playerA = game.getPlayers().get(0);
        playerB = game.getPlayers().get(1);

        // game test without god card.
        game.placeWorker(playerA, Location.get(1, 1));
        game.placeWorker(playerA, Location.get(3, 3));
        game.placeWorker(playerB, Location.get(1, 3));
        game.placeWorker(playerB, Location.get(3, 1));
        /* initial state of board.
         * - - - - -
         * - A - B -
         * - - - - -
         * - B - A -
         * - - - - -
         */
    }

    // ------------------ game without god ------------------
    @Test
    public void integrationTest_withoutGod_testSuccess() {
        game.moveWorker(playerA, Location.get(1, 1), Location.get(2, 1));
        game.build(playerA, Location.get(2, 1), Location.get(2, 2));
        game.moveWorker(playerB, Location.get(1, 3), Location.get(2, 3));
        game.build(playerB, Location.get(2, 3), Location.get(2, 2));

        game.moveWorker(playerA, Location.get(2, 1), Location.get(1, 1));
        game.build(playerA, Location.get(1, 1), Location.get(2, 1));
        game.moveWorker(playerB, Location.get(2, 3), Location.get(1, 3));
        game.build(playerB, Location.get(1, 3), Location.get(2, 2));

        game.moveWorker(playerA, Location.get(1, 1), Location.get(2, 1));
        game.build(playerA, Location.get(2, 1), Location.get(1, 2));
        game.moveWorker(playerB, Location.get(1, 3), Location.get(2, 3));
        game.build(playerB, Location.get(2, 3), Location.get(1, 2));

        game.moveWorker(playerA, Location.get(2, 1), Location.get(1, 2));
        game.build(playerA, Location.get(1, 2), Location.get(1, 1));
        game.moveWorker(playerB, Location.get(3, 1), Location.get(4, 0));
        game.build(playerB, Location.get(4, 0), Location.get(4, 1));

        assertNull(game.getWinner());
        game.moveWorker(playerA, Location.get(1, 2), Location.get(2, 2));
        assertEquals(playerA, game.getWinner());
    }

    @Test
    public void integrationTest_withoutGod_testInValidMove_workerNotExist() {
        assertFalse(game.moveWorker(playerA, Location.get(0, 0), Location.get(1, 1)));
    }

    @Test
    public void integrationTest_withoutGod_testInValidMove_moveTooFar() {
        assertFalse(game.moveWorker(playerA, Location.get(1, 1), Location.get(4, 4)));
    }

    @Test
    public void integrationTest_withoutGod_testInValidMove_Occupied() {
        game.moveWorker(playerA, Location.get(1, 1), Location.get(2, 1));
        game.build(playerA, Location.get(2, 1), Location.get(2, 2));
        assertFalse(game.moveWorker(playerB, Location.get(3, 1), Location.get(2, 1)));
    }

    @Test
    public void integrationTest_withoutGod_testInValidMove_TwoLevelHigh() {
        game.moveWorker(playerA, Location.get(1, 1), Location.get(2, 1));
        game.build(playerA, Location.get(2, 1), Location.get(2, 2));
        game.moveWorker(playerB, Location.get(1, 3), Location.get(2, 3));
        game.build(playerB, Location.get(2, 3), Location.get(2, 2));
        assertFalse(game.moveWorker(playerA, Location.get(2, 1), Location.get(2, 2)));
    }

    @Test
    public void integrationTest_withoutGod_testInValidMove_Domed() {
        game.moveWorker(playerA, Location.get(1, 1), Location.get(2, 1));
        game.build(playerA, Location.get(2, 1), Location.get(2, 2));
        game.moveWorker(playerB, Location.get(1, 3), Location.get(2, 3));
        game.build(playerB, Location.get(2, 3), Location.get(2, 2));

        game.moveWorker(playerA, Location.get(2, 1), Location.get(1, 2));
        game.build(playerA, Location.get(1, 2), Location.get(2, 2));
        game.moveWorker(playerB, Location.get(2, 3), Location.get(1, 3));
        game.build(playerB, Location.get(1, 3), Location.get(2, 2));

        assertFalse(game.moveWorker(playerA, Location.get(1, 1), Location.get(2, 2)));
    }



    @Test
    public void integrationTest_withoutGod_wrongSequence_doubleMove() {
        game.moveWorker(playerA, Location.get(1, 1), Location.get(2, 1));
        assertFalse(game.moveWorker(playerA, Location.get(2, 1), Location.get(2, 2)));
    }

    @Test
    public void integrationTest_withoutGod_wrongSequence_wrongPlayer() {
        game.moveWorker(playerA, Location.get(1, 1), Location.get(2, 1));
        assertFalse(game.moveWorker(playerB, Location.get(1, 3), Location.get(0, 3)));
    }

    @Test
    public void integrationTest_withoutGod_wrongSequence_doubleBuild() {
        game.moveWorker(playerA, Location.get(1, 1), Location.get(2, 1));
        game.build(playerA, Location.get(2, 1), Location.get(2, 2));
        assertFalse(game.build(playerA, Location.get(2, 1), Location.get(2, 2)));
    }

    @Test
    public void integrationTest_withoutGod_wrongSequence_manipulateDifferentWorkers() {
        game.moveWorker(playerA, Location.get(1, 1), Location.get(2, 1));
        assertFalse(game.build(playerA, Location.get(3, 3), Location.get(4, 4)));
    }

    // ------------------ game with god ------------------

    // ------------------ game with athena ------------------
    @Test
    public void test_Athena_castPower() {
        // assign Athena to playerA.
        game.assignGameLogic(playerA, new AthenaGameLogic(new BasicGameLogic()));

        game.moveWorker(playerA, Location.get(1, 1), Location.get(2, 1));
        game.build(playerA, Location.get(2, 1), Location.get(2, 2));
        game.moveWorker(playerB, Location.get(1, 3), Location.get(2, 3));
        game.build(playerB, Location.get(2, 3), Location.get(2, 4));

        // athena move up.
        game.moveWorker(playerA, Location.get(2, 1), Location.get(2, 2));
        game.build(playerA, Location.get(2, 2), Location.get(2, 1));
        // opponent is blocked to move up.

        assertFalse(game.moveWorker(playerB, Location.get(2, 3), Location.get(2, 4)));
    }

    @Test
    public void test_Athena_powerOffAfterOneFlatMove() {
        // assign Athena to playerA.
        game.assignGameLogic(playerA, new AthenaGameLogic(new BasicGameLogic()));

        game.moveWorker(playerA, Location.get(1, 1), Location.get(2, 1));
        game.build(playerA, Location.get(2, 1), Location.get(2, 2));
        game.moveWorker(playerB, Location.get(1, 3), Location.get(2, 3));
        game.build(playerB, Location.get(2, 3), Location.get(2, 4));

        // athena move up.
        game.moveWorker(playerA, Location.get(2, 1), Location.get(2, 2));
        game.build(playerA, Location.get(2, 2), Location.get(2, 1));
        // flat move by opponent worker
        game.moveWorker(playerB, Location.get(2, 3), Location.get(1, 3));
        game.build(playerB, Location.get(1, 3), Location.get(1, 4));

        // athena move down.
        game.moveWorker(playerA, Location.get(2, 2), Location.get(1, 2));
        game.build(playerA, Location.get(1, 2), Location.get(2, 2));
        // opponent worker can move up now.
        assertTrue(game.moveWorker(playerB, Location.get(1, 3), Location.get(1, 4)));
    }

    // ------------------ game with pan ------------------
    @Test
    public void test_Pan_winningCondition() {
        // assign Athena to playerA.
        game.assignGameLogic(playerB, new PanGameLogic(new BasicGameLogic()));

        game.moveWorker(playerA, Location.get(1, 1), Location.get(2, 1));
        game.build(playerA, Location.get(2, 1), Location.get(2, 2));
        game.moveWorker(playerB, Location.get(1, 3), Location.get(2, 3));
        game.build(playerB, Location.get(2, 3), Location.get(2, 2));

        game.moveWorker(playerA, Location.get(2, 1), Location.get(1, 1));
        game.build(playerA, Location.get(1, 1), Location.get(1, 2));
        game.moveWorker(playerB, Location.get(2, 3), Location.get(1, 2));
        game.build(playerB, Location.get(1, 2), Location.get(0, 1));

        game.moveWorker(playerA, Location.get(3, 3), Location.get(3, 4));
        game.build(playerA, Location.get(3, 4), Location.get(4, 4));
        game.moveWorker(playerB, Location.get(1, 2), Location.get(2, 2));
        game.build(playerB, Location.get(2, 2), Location.get(1, 2));

        game.moveWorker(playerA, Location.get(3, 4), Location.get(3, 3));
        game.build(playerA, Location.get(3, 3), Location.get(4, 4));
        assertNull(game.getWinner());
        // pan move down by 2 levels.
        game.moveWorker(playerB, Location.get(2, 2), Location.get(2, 3));
        assertEquals(playerB, game.getWinner());
    }



    public static void notmain(String[] args) {

        Game game = new Game();
        Player player1 = game.getPlayers().get(0);
        Player player2 = game.getPlayers().get(1);

        // game test without god card.
        game.placeWorker(player1, Location.get(0, 0));
        game.placeWorker(player1, Location.get(4, 4));
        game.placeWorker(player2, Location.get(4, 0));
        game.placeWorker(player2, Location.get(0, 4));




//        // Minotaur test
//        game.assignGameLogic(player2, new MinotaurGameLogic(new BasicGameLogic()));
//        game.placeWorker(player1, Location.get(0, 0));
//        game.placeWorker(player1, Location.get(4, 4));
//        game.placeWorker(player2, Location.get(4, 0));
//        game.placeWorker(player2, Location.get(0, 4));
//        game.printBoard();
//
//        game.moveWorker(player1, Location.get(0, 0), Location.get(1, 1));
//        game.moveWorker(player2, Location.get(0, 4), Location.get(1, 4));
//        game.printBoard();
//        // move worker from (1,4) to (1,1). suppose worker in (1,1) move back to (0,0)
//        game.moveWorker(player2, Location.get(1, 4), Location.get(1, 1));
//        game.printBoard();
//
//        // oppo worker has no prev state, should fail
//        System.out.println(game.moveWorker(player2, Location.get(1, 1), Location.get(0, 0)));
//        game.printBoard();


//        // Apollo test.
//        game.assignGameLogic(player2, new ApolloGameLogic(new BasicGameLogic()));
//        game.placeWorker(player1, Location.get(0, 0));
//        game.placeWorker(player1, Location.get(4, 4));
//        game.placeWorker(player2, Location.get(4, 0));
//        game.placeWorker(player2, Location.get(0, 4));
//        game.printBoard();
//
//        game.moveWorker(player1, Location.get(0, 0), Location.get(1, 1));
//        game.moveWorker(player2, Location.get(0, 4), Location.get(1, 4));
//        game.printBoard();
//
//        // move worker from (1,4) to (1,1). suppose worker in (1,1) move back to (1,4)
//        game.moveWorker(player2, Location.get(1, 4), Location.get(1, 1));
//        game.printBoard();

//        // Athena test.
//        game.assignGameLogic(player2, new AthenaGameLogic(new BasicGameLogic()));
//        game.placeWorker(player1, Location.get(0, 0));
//        game.placeWorker(player1, Location.get(4, 4));
//        game.placeWorker(player2, Location.get(4, 0));
//        game.placeWorker(player2, Location.get(0, 4));
//        game.printBoard();
//
//        // move up athena
//        game.build(player2, Location.get(0,4), Location.get(0,3));
//        game.moveWorker(player2, Location.get(0, 4), Location.get(0, 3));
//        game.printBoard();
//
//        // move up opponent, should fail
//        game.build(player1, Location.get(0, 0), Location.get(0,1));
//        game.moveWorker(player1, Location.get(0, 0), Location.get(0, 1));
//        game.printBoard();


//        // Pan test.
//        game.assignGameLogic(player2, new PanGameLogic(new BasicGameLogic()));
//        game.placeWorker(player1, Location.get(0, 0));
//        game.placeWorker(player1, Location.get(4, 4));
//        game.placeWorker(player2, Location.get(4, 0));
//        game.placeWorker(player2, Location.get(0, 4));
//        game.printBoard();
//
//        // move the pan to level 2 tower.
//        game.build(player2, Location.get(0,4), Location.get(0,3));
//        game.build(player2, Location.get(0,4), Location.get(0,3));
//        game.build(player2, Location.get(0,4), Location.get(1,4));
//        game.moveWorker(player2, Location.get(0, 4), Location.get(1, 4));
//        game.moveWorker(player2, Location.get(1, 4), Location.get(0, 3));
//        game.printBoard();
//
//        // then move it down to flat.
//        game.moveWorker(player2, Location.get(0, 3), Location.get(0, 2));
//        game.printBoard();


//        // test sequence handler.
//        game.assignGameLogic(player1, new ArtemisGameLogic(new BasicGameLogic()));
//        game.placeWorker(player1, Location.get(0, 0));
//        game.placeWorker(player1, Location.get(4, 4));
//        game.placeWorker(player2, Location.get(4, 0));
//        game.placeWorker(player2, Location.get(0, 4));
//        game.printBoard();
//
//        // round 1
//        // player 1 turn.
//        game.moveWorker(player1, Location.get(0, 0), Location.get(1, 0));
//        game.moveWorker(player1, Location.get(1, 0), Location.get(1, 1));
//        game.build(player1, Location.get(1, 1), Location.get(0, 0));
//        game.printBoard();
//        // player 2 turn.
//        game.moveWorker(player2, Location.get(4, 0), Location.get(4, 1));
//        game.build(player2, Location.get(4, 1), Location.get(4, 0));
//        game.printBoard();
//
//        // round 2
//        // player 1 turn.
//        game.moveWorker(player1, Location.get(1, 1), Location.get(1, 2));
//        game.skipAction(player1);
//        game.build(player1, Location.get(1, 2), Location.get(1, 1));
//        game.printBoard();
//        // player 2 turn.
//        game.moveWorker(player2, Location.get(4, 1), Location.get(3, 1));
//        game.build(player2, Location.get(3, 1), Location.get(4, 0));
//        game.printBoard();

//        // Artemis test.
//        game.assignGameLogic(player1, new ArtemisGameLogic(new BasicGameLogic()));
//        game.placeWorker(player1, Location.get(0, 0));
//        game.placeWorker(player1, Location.get(4, 4));
//        game.placeWorker(player2, Location.get(4, 0));
//        game.placeWorker(player2, Location.get(0, 4));
//        game.printBoard();
//
//        game.moveWorker(player1, Location.get(0, 0), Location.get(1, 1));
//        game.skipAction(player1);
//        game.build(player1, Location.get(1, 1), Location.get(1, 0));
//        game.printBoard();
//
//        game.moveWorker(player2, Location.get(4, 0), Location.get(3, 0));
//        game.printBoard();


//        // Demeter test.
//        game.assignGameLogic(player1, new DemeterGameLogic(new BasicGameLogic()));
//        game.placeWorker(player1, Location.get(0, 0));
//        game.placeWorker(player1, Location.get(4, 4));
//        game.placeWorker(player2, Location.get(4, 0));
//        game.placeWorker(player2, Location.get(0, 4));
//        game.printBoard();
//
//        game.moveWorker(player1, Location.get(0, 0), Location.get(1, 1));
//        game.build(player1, Location.get(1, 1), Location.get(1, 0));
//        game.build(player1, Location.get(1, 1), Location.get(1, 2));
//        game.printBoard();
//
//        game.moveWorker(player2, Location.get(0, 4), Location.get(0, 3));
//        game.build(player2, Location.get(0, 3), Location.get(1, 2));
//        game.printBoard();

//        // Atlas test.
//        game.assignGameLogic(player1, new AtlasGameLogic(new BasicGameLogic()));
//        game.placeWorker(player1, Location.get(0, 0));
//        game.placeWorker(player1, Location.get(4, 4));
//        game.placeWorker(player2, Location.get(4, 0));
//        game.placeWorker(player2, Location.get(0, 4));
//        game.printBoard();
//
//        game.moveWorker(player1, Location.get(0, 0), Location.get(0, 1));
//        game.build(player1, Location.get(0, 1), Location.get(0, 0));
//        game.build(player1, Location.get(0, 1), Location.get(0, 0));
//        game.printBoard();
//
//        game.moveWorker(player2, Location.get(0, 4), Location.get(0, 3));
//        game.build(player2, Location.get(0, 3), Location.get(0, 2));
//        game.printBoard();

//        // Hephaestus test, 2x build
//        game.assignGameLogic(player1, new HephaestusGameLogic(new BasicGameLogic()));
//        game.placeWorker(player1, Location.get(0, 0));
//        game.placeWorker(player1, Location.get(4, 4));
//        game.placeWorker(player2, Location.get(4, 0));
//        game.placeWorker(player2, Location.get(0, 4));
//        game.printBoard();
//
//        game.moveWorker(player1, Location.get(0, 0), Location.get(0, 1));
//        game.build(player1, Location.get(0, 1), Location.get(0, 0));
//        game.build(player1, Location.get(0, 1), Location.get(0, 0));
//        game.printBoard();
//
//        game.moveWorker(player2, Location.get(0, 4), Location.get(0, 3));
//        game.build(player2, Location.get(0, 3), Location.get(0, 2));
//        game.printBoard();
//
//        game.moveWorker(player1, Location.get(0, 1), Location.get(1, 1));
//        System.out.println(game.build(player1, Location.get(1, 1), Location.get(0, 0)));
//        System.out.println(game.build(player1, Location.get(1, 1), Location.get(0, 0)));
//        game.printBoard();

        // Hermes test, unlimited 0-lvl-change move.
        game.assignGameLogic(player1, new HermesGameLogic(new BasicGameLogic()));
        game.placeWorker(player1, Location.get(0, 0));
        game.placeWorker(player1, Location.get(4, 4));
        game.placeWorker(player2, Location.get(4, 0));
        game.placeWorker(player2, Location.get(0, 4));


        game.moveWorker(player1, Location.get(0, 0), Location.get(0, 1));
        game.skipAction(player1);
        game.build(player1, Location.get(0,1), Location.get(0,0));


        game.moveWorker(player2, Location.get(0, 4), Location.get(1, 4));
        game.build(player2, Location.get(1, 4), Location.get(0, 4));


        game.moveWorker(player1, Location.get(0, 1), Location.get(1, 0));
        game.moveWorker(player1, Location.get(1, 0), Location.get(0, 0));
        game.build(player1, Location.get(0, 0), Location.get(1, 1));



    }

}
