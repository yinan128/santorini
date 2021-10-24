package edu.cmu.cs214.hw3.core;

import edu.cmu.cs214.hw3.gameLogic.AthenaGameLogic;
import edu.cmu.cs214.hw3.gameLogic.BasicGameLogic;
import edu.cmu.cs214.hw3.gameLogic.MinotaurGameLogic;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.position.Location;

public class GameTest {
    public static void main(String[] args) {

        Game game = new Game();
        Player player1 = game.getPlayers().get(0);
        Player player2 = game.getPlayers().get(1);

        // Minotaur test
        game.assignGameLogic(player2, new MinotaurGameLogic(new BasicGameLogic()));
        game.placeWorker(player1, Location.get(0, 0));
        game.placeWorker(player1, Location.get(4, 4));
        game.placeWorker(player2, Location.get(4, 0));
        game.placeWorker(player2, Location.get(0, 4));
        game.printBoard();

        game.moveWorker(player1, Location.get(0, 0), Location.get(1, 1));
        game.moveWorker(player2, Location.get(0, 4), Location.get(1, 4));
        game.printBoard();
        // move worker from (1,4) to (1,1). suppose worker in (1,1) move back to (0,0)
        game.moveWorker(player2, Location.get(1, 4), Location.get(1, 1));
        game.printBoard();

        // oppo worker has no prev state, should fail
        System.out.println(game.moveWorker(player2, Location.get(1, 1), Location.get(0, 0)));
        game.printBoard();




    }

}
