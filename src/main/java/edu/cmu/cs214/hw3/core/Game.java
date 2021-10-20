package edu.cmu.cs214.hw3.core;

import edu.cmu.cs214.hw3.position.Direction;
import edu.cmu.cs214.hw3.playground.Field;
import edu.cmu.cs214.hw3.position.Location;
import edu.cmu.cs214.hw3.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {


    private static final int PLAYER_NUM = 2;

    private boolean gameOver;
    private int round;
    private final List<Player> players;
    private Player currPlayer;
    private final Board gameBoard;


    public Game() {
        players = new ArrayList<>(PLAYER_NUM);
        for (int i = 0; i < PLAYER_NUM; i++) {
            players.add(new Player(i));
        }

        gameOver = false;
        round = 0;
        currPlayer = players.get(round);
        gameBoard = new Board();
    }

    public boolean addStartLocation(int playerIndex, int row, int col) {
        Player p = players.get(playerIndex);
        if (isOccupied(new Location(row, col))) {
            System.out.println("Current Field is occupied");
            return false;
        }
        boolean success = p.addWorker(new Location(row, col));
        if (success) {
            occupy(new Location(row, col));
        }
        return success;

    }


    public void startRound() {
        currPlayer = players.get(round % PLAYER_NUM);
        round++;
    }

    public boolean moveWorker(int workerIndex, Direction dir) {
        Location destination;
        try {
            destination = currPlayer.getDestination(workerIndex, dir);
        } catch (IllegalArgumentException e) {
            // worker index out of bound.
            System.out.println(e.getMessage());
            return false;
        }
        if (!isCoordOnBoard(destination)) {
            System.out.println("destination is not on board.");
            return false;
        }
        if (isOccupied(destination)) {
            System.out.println("destination is already occupied.");
            return false;
        }
        Location currLocation = currPlayer.getWorkerLocation(workerIndex);
        if (!Field.isMovable(
                gameBoard.getField(currLocation),
                gameBoard.getField(destination))) {
            System.out.println("destination is unreachable");
            return false;
        }
        // before actually move the worker, change the inner state of field.
        free(currLocation);
        occupy(destination);
        System.out.println("dest: " + destination);

        // end game.
        if (gameBoard.getField(destination).isBlockFull()) {
            gameOver = true;
        }

        // failsafe.
        return currPlayer.moveWorker(workerIndex, dir);
    }

    public boolean build(int workerIndex, Direction dir) {
        // build action always follows the move, so no need to verify buildDestination (failsafe)
        Location buildDestination = currPlayer.getDestination(workerIndex, dir);
        System.out.println("buildDestination: " + buildDestination);
        Field fieldToBuild = gameBoard.getField(buildDestination);
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
        return gameBoard.getField(location).isOccupied();
    }

    private boolean isCoordOnBoard(Location location) {
        return gameBoard.isCoordOnBoard(location);
    }

    private void occupy(Location location) {
        gameBoard.getField(location).occupy();
    }

    private void free(Location location) {
        gameBoard.getField(location).free();
    }

    private String getCurrentPlayer() {
        return currPlayer.toString();
    }
}
