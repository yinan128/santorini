package edu.cmu.cs214.hw3.core;

import edu.cmu.cs214.hw3.gameLogic.BasicGameLogic;
import edu.cmu.cs214.hw3.gameLogic.GameLogic;
import edu.cmu.cs214.hw3.listeners.EventListener;
import edu.cmu.cs214.hw3.listeners.LogicController;
import edu.cmu.cs214.hw3.listeners.SequenceHandler;
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
    private Player winner;

    private final Board board;
    private final Map<Player, GameLogic> logics;
    private final EventListener gameLogicController;
    private final SequenceHandler sequenceHandler;


    public Game() {
        players = new ArrayList<>(PLAYER_NUM);
        for (int i = 0; i < PLAYER_NUM; i++) {
            players.add(new Player(i));
        }

        gameOver = false;
        round = 0;
        currPlayer = players.get(round);
        winner = null;
        board = new Board();

        logics = new HashMap<>();
        logics.put(players.get(0), new BasicGameLogic());
        logics.put(players.get(1), new BasicGameLogic());
        gameLogicController = new LogicController(logics);
        sequenceHandler = new SequenceHandler(players);
        logics.values().forEach(logic -> {
            logic.subscribe(gameLogicController);
            logic.subscribe(sequenceHandler);
        });
    }

    public void assignGameLogic(Player player, GameLogic gameLogic) {
        GameLogic prevLogic = logics.get(player);
        prevLogic.getEventListeners().forEach(gameLogic::subscribe);
        logics.put(player, gameLogic);
    }

    /**
     * the action to place the worker at a certain location at the start of game.
     * @param player the player who places the worker.
     * @param location the location where the worker is to be placed.
     * @return true if placement is successful.
     */
    public boolean placeWorker(Player player, Location location) {
        GameLogic currPlayerLogic = logics.get(player);
        Worker workerToPlace = new Worker(location, player);
        if (player.workerFull()) return false;
        boolean placeSuccess = currPlayerLogic.placeWorker(board, workerToPlace, location);
        if (!placeSuccess) return false;
        player.addWorker();
        return true;
    }


    public void startRound() {
        currPlayer = players.get(round % PLAYER_NUM);
        round++;
    }

    /**
     * game command to move a worker to a destination.
     * @param player the owner of the worker to be moved.
     * @param start the starting location which holds the worker to be moved.
     * @param destination destination of the movement.
     * @return true if move is successful, otherwise false.
     */
    public boolean moveWorker(Player player, Location start, Location destination) {
        if (!sequenceHandler.isValidAction(player, WorkerAction.MOVE)) {
            System.out.println("invalid move action.");
            return false;
        }
        GameLogic currPlayerLogic = logics.get(player);
        if (!currPlayerLogic.isValidMove(board, start, destination) || !authorizedWorker(player, start)) {
            return false;
        }

        // movement is valid, apply actual move action.
        boolean moveSuccess = currPlayerLogic.move(board, start, destination);
        // move could fail.
        if (!moveSuccess) return false;

        System.out.println("Move success. Destination: " + destination);

        // check winning case.
        if (currPlayerLogic.isWinningCase(board, destination)) {
            gameOver = true;
            winner = player;
            System.out.println("Game over. Winner is " + winner.toString());
        }

        return true;
    }

    private boolean authorizedWorker(Player player, Location start) {
        return player == board.getWorkerOwner(start);
    }

    /**
     * game command to make a worker build on a given location.
     * @param player the owner of the worker to build.
     * @param start the starting location which holds the worker to build.
     * @param location destination of the build action.
     * @return true if build is successful, otherwise false.
     */
    public boolean build(Player player, Location start, Location location) {
        if (!sequenceHandler.isValidAction(player, WorkerAction.BUILD)) {
            System.out.println("invalid build action.");
            return false;
        }
        GameLogic currPlayerLogic = logics.get(player);
        if (!currPlayerLogic.isBuildable(board, start, location) || !authorizedWorker(player, start)) {
            return false;
        }

        boolean buildSuccess = currPlayerLogic.build(board, location);
        if (!buildSuccess) return false;

        System.out.println("Build success. Destination: " + location);
        return true;
    }

    public void skipAction(Player player) {
        GameLogic currPlayerLogic = logics.get(player);
        currPlayerLogic.skip();
    }

    public void printBoard() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Location currLoc = Location.get(i, j);
                if (board.getField(currLoc).hasWorker()){
                    System.out.print(board.getField(currLoc).getWorker().getPlayer().symbol());
                } else {
                    if (board.getField(currLoc).hasDome()) {
                        System.out.print("X");
                    } else {
                        int lvl = board.getField(currLoc).getLevel();
                        if (lvl == 0) {
                            System.out.print("-");
                        } else {
                            System.out.print(lvl);
                        }
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


    public Map<Player, GameLogic> getLogics() {
        return logics;
    }
}
