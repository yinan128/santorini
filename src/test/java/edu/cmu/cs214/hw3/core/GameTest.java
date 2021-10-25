package edu.cmu.cs214.hw3.core;

import edu.cmu.cs214.hw3.player.Player;

public class GameTest {
    public static void main(String[] args) {

        Game game = new Game();
        Player player1 = game.getPlayers().get(0);
        Player player2 = game.getPlayers().get(1);

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

//        // Hermes test, unlimited 0-lvl-change move.
//        game.assignGameLogic(player1, new HermesGameLogic(new BasicGameLogic()));
//        game.placeWorker(player1, Location.get(0, 0));
//        game.placeWorker(player1, Location.get(4, 4));
//        game.placeWorker(player2, Location.get(4, 0));
//        game.placeWorker(player2, Location.get(0, 4));
//        game.printBoard();
//
//        game.moveWorker(player1, Location.get(0, 0), Location.get(0, 1));
//        game.skipAction(player1);
//        game.build(player1, Location.get(0,1), Location.get(0,0));
//        game.printBoard();
//
//        game.moveWorker(player2, Location.get(0, 4), Location.get(1, 4));
//        game.build(player2, Location.get(1, 4), Location.get(0, 4));
//        game.printBoard();
//
//        game.moveWorker(player1, Location.get(0, 1), Location.get(1, 0));
//        game.moveWorker(player1, Location.get(1, 0), Location.get(0, 0));
//        game.build(player1, Location.get(0, 0), Location.get(1, 1));
//        game.printBoard();


    }

}
