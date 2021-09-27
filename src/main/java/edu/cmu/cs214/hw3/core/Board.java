package edu.cmu.cs214.hw3.core;

import edu.cmu.cs214.hw3.position.Direction;
import edu.cmu.cs214.hw3.playground.Field;
import edu.cmu.cs214.hw3.position.Location;
import edu.cmu.cs214.hw3.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {


    private static final int PLAYER_NUM = 2;
    private static final int COLS = 5;
    private static final int ROWS = 5;

    private boolean gameOver;
    private int round;
    private final List<Player> players;
    private Player currPlayer;
    private final Map<Location, Field> fieldMap;


    public Board() {
        players = new ArrayList<>(PLAYER_NUM);
        for (int i = 0; i < PLAYER_NUM; i++) {
            players.add(new Player(i));
        }

        gameOver = false;
        round = 0;
        currPlayer = players.get(round);
        fieldMap = new HashMap<>();

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                fieldMap.put(new Location(i, j), new Field());
            }
        }
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
                fieldMap.get(currLocation),
                fieldMap.get(destination))) {
            System.out.println("destination is unreachable");
            return false;
        }
        // before actually move the worker, change the inner state of field.
        free(currLocation);
        occupy(destination);
        System.out.println("dest: " + destination);

        // end game.
        if (fieldMap.get(destination).isBlockFull()) {
            gameOver = true;
        }

        // failsafe.
        return currPlayer.moveWorker(workerIndex, dir);
    }

    public boolean build(int workerIndex, Direction dir) {
        // build action always follows the move, so no need to verify buildDestination (failsafe)
        Location buildDestination = currPlayer.getDestination(workerIndex, dir);
        System.out.println("buildDestination: " + buildDestination);
        Field fieldToBuild = fieldMap.get(buildDestination);
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
        return fieldMap.get(location).isOccupied();
    }

    private boolean isCoordOnBoard(Location location) {
        return fieldMap.containsKey(location);
    }

    private void occupy(Location location) {
        fieldMap.get(location).occupy();
    }

    private void free(Location location) {
        fieldMap.get(location).free();
    }

    private String getCurrentPlayer() {
        return currPlayer.toString();
    }
}
