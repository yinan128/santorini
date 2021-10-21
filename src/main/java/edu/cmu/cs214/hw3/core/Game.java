package edu.cmu.cs214.hw3.core;

import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.position.Direction;
import edu.cmu.cs214.hw3.playground.Field;
import edu.cmu.cs214.hw3.position.Location;
import edu.cmu.cs214.hw3.player.Player;

import java.util.*;

public class Game {


    private static final int PLAYER_NUM = 2;

    private boolean gameOver;
    private int round;
    private final List<Player> players;
    private Player currPlayer;
    private final Board board;
    private final Map<Player, GameLogic> logics;


    public Game() {
        players = new ArrayList<>(PLAYER_NUM);
        for (int i = 0; i < PLAYER_NUM; i++) {
            players.add(new Player(i));
        }

        gameOver = false;
        round = 0;
        currPlayer = players.get(round);
        board = new Board();

        logics = new HashMap<>();
        logics.put(players.get(0), new BasicGameLogic());
        logics.put(players.get(1), new BasicGameLogic());
    }

    public boolean addStartLocation(int playerIndex, int row, int col) {
        Player p = players.get(playerIndex);
        if (isOccupied(Location.get(row, col))) {
            System.out.println("Current Field is occupied");
            return false;
        }
        boolean success = p.addWorker(Location.get(row, col));
        if (success) {
            occupy(Location.get(row, col), p.getLastWorker());
        }
        return success;

    }


    public void startRound() {
        currPlayer = players.get(round % PLAYER_NUM);
        round++;
    }

    public boolean moveWorker(Player player, int workerIndex, Location destination) {
        GameLogic currLogic = logics.get(player);
        Location start = player.getWorkerLocation(workerIndex);
        if (!currLogic.isValidMove(board, start, destination)) {
            return false;
        }
        //todo move on from here. 10.19
        // before actually move the worker, change the inner state of field.
        migrateWorker(start, destination);
//        free(start);
//        occupy(destination);
        System.out.println("dest: " + destination);

        // end game.
        if (currLogic.isWinningCase(board, destination)) {
            gameOver = true;
        }

        // failsafe.
        return currPlayer.moveWorker(workerIndex, destination);
    }

    private void migrateWorker(Location start, Location destination) {
        board.migrateWorker(start, destination);
    }

    public boolean build(int workerIndex, Direction dir) {
        // build action always follows the move, so no need to verify buildDestination (failsafe)
        Location buildDestination = currPlayer.getDestination(workerIndex, dir);
        System.out.println("buildDestination: " + buildDestination);
        Field fieldToBuild = board.getField(buildDestination);
        if (fieldToBuild == null) {
            System.out.println("destination is not on board.");
            return false;
        }
        return fieldToBuild.build();
    }

    public boolean isGameOver() {
        System.out.println("Winner is:" + getCurrentPlayer());
        return gameOver;
    }

    private boolean isOccupied(Location location) {
        return board.getField(location).isOccupied();
    }


    private void occupy(Location location, Worker worker) {
        board.getField(location).occupy(worker);
    }

    private void free(Location location) {
        board.getField(location).free();
    }

    private String getCurrentPlayer() {
        return currPlayer.toString();
    }
}
