package edu.cmu.cs214.hw3.core;

import edu.cmu.cs214.hw3.gameLogic.BasicGameLogic;
import edu.cmu.cs214.hw3.gameLogic.GameLogic;
import edu.cmu.cs214.hw3.listeners.EventListener;
import edu.cmu.cs214.hw3.listeners.LogicManager;
import edu.cmu.cs214.hw3.player.Worker;
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
        logics.values().forEach(logic -> logic.subscribe(gameLogicManager));
    }

    public void assignGameLogic(Player player, GameLogic gameLogic) {
        logics.put(player, gameLogic);
    }

    public boolean placeWorker(Player player, Location location) {
        GameLogic currPlayerLogic = logics.get(player);
        Worker workerToPlace = new Worker(location, player);
        if (player.workerFull()) return false;
        return currPlayerLogic.placeWorker(board, workerToPlace, location);
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
        boolean moveSuccess = currPlayerLogic.move(board, workerToMove, destination);
        // move could fail.
        if (!moveSuccess) return false;

        System.out.println("Move success. Destination: " + destination);

        // check winning case.
        if (currPlayerLogic.isWinningCase(board, destination)) {
            gameOver = true;
        }

        return true;
    }

    /**
     * game command to make a worker build on a given location.
     * @param player the owner of the worker to build.
     * @param workerIndex the index of the selected worker in the worker list.
     * @param location destination of the build action.
     * @return true if build is successful, otherwise false.
     */
    public boolean build(Player player, int workerIndex, Location location) {
        GameLogic currPlayerLogic = logics.get(player);
        Worker workerToBuild = player.getWorker(workerIndex);
        if (!currPlayerLogic.isBuildable(board, workerToBuild, location)) {
            return false;
        }

        boolean buildSuccess = currPlayerLogic.build(board, location);
        if (!buildSuccess) return false;

        System.out.println("Build success. Destination: " + location);
        return true;
    }


    public boolean isGameOver() {
        System.out.println("Winner is:" + getCurrentPlayer());
        return gameOver;
    }

    private String getCurrentPlayer() {
        return currPlayer.toString();
    }

    public void printBoard() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Location currLoc = Location.get(i, j);
                if (board.getField(currLoc).hasWorker()){
                    System.out.print(board.getField(currLoc).getWorker().getPlayer().symbol());
                } else {
                    int lvl = board.getField(currLoc).getLevel();
                    if (lvl == 0) {
                        System.out.print("-");
                    } else {
                        System.out.print(lvl);
                    }
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }
}
