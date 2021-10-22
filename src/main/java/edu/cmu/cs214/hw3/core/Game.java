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
    private final EventListener gameLogicManager;


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
        gameLogicManager = new LogicManager(logics);
        logics.values().forEach(l -> l.subscribe(gameLogicManager));
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

    /**
     * game command to move a worker to a destination.
     * @param player the owner of the worker to be moved.
     * @param workerIndex the index of the selected worker in the worker list.
     * @param destination destination of the movement.
     * @return true if move is successful, otherwise false.
     */
    public boolean moveWorker(Player player, int workerIndex, Location destination) {
        GameLogic currPlayerLogic = logics.get(player);
        Worker workerToMove = player.getWorker(workerIndex);
        if (!currPlayerLogic.isValidMove(board, workerToMove, destination)) {
            return false;
        }

        // movement is valid, apply actual move action.
        boolean moveResult = currPlayerLogic.move(board, workerToMove, destination);
        // move could fail.
        if (!moveResult) {
            return moveResult;
        }

        System.out.println("Move success. Destination: " + destination);

        // check winning case.
        if (currPlayerLogic.isWinningCase(board, destination)) {
            gameOver = true;
        }

        return true;
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
