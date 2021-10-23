package edu.cmu.cs214.hw3.core;

import edu.cmu.cs214.hw3.gameLogic.BasicGameLogic;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.position.Location;

public class GameTest {
    public static void main(String[] args) {
        Game game = new Game();
        Player player1 = game.getPlayers().get(0);
        Player player2 = game.getPlayers().get(1);
        System.out.println(game.placeWorker(player1, Location.get(0, 0)));
        System.out.println(game.placeWorker(player1, Location.get(4, 4)));
        System.out.println(game.placeWorker(player2, Location.get(4, 0)));
        System.out.println(game.placeWorker(player2, Location.get(0, 4)));
        game.printBoard();

        game.moveWorker(player1, 0, Location.get(1, 1));
        game.moveWorker(player2, 1, Location.get(1, 4));
        game.printBoard();

        game.build(player1, 0, Location.get(0,0));
//        game.build(player1, 0, Location.get(0,1));
//        game.build(player1, 0, Location.get(0,2));
//        game.build(player2, 0, Location.get(4,1));
//        game.build(player2, 0, Location.get(3,0));
//        game.build(player2, 0, Location.get(3,1));

        game.printBoard();

        System.out.println(game.getBoard().getLocationPerimeter(Location.get(1,1)));


    }

}
