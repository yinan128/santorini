package edu.cmu.cs214.hw3.core;

import edu.cmu.cs214.hw3.gameLogic.BasicGameLogic;
import edu.cmu.cs214.hw3.gameLogic.GameLogic;
import edu.cmu.cs214.hw3.listeners.*;
import edu.cmu.cs214.hw3.listeners.EventListener;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.util.Location;
import edu.cmu.cs214.hw3.player.Player;

import java.util.*;

/**
 * Game controller.
 */
public class Game {


    private static final int PLAYER_NUM = 2;

    private final List<Player> players;
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
        placeWorker(players.get(0), Location.get(0,0));
        placeWorker(players.get(0), Location.get(4,4));
        placeWorker(players.get(1), Location.get(0,4));
        placeWorker(players.get(1), Location.get(4,0));
    }

    /**
     * method to assign an advanced game logic to the designated player
     * @param player the player to be assigned the game logic.
     * @param gameLogic an advanced game logic.
     */
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


    /**
     * game command to move a worker to a destination.
     * @param player the owner of the worker to be moved.
     * @param start the starting location which holds the worker to be moved.
     * @param destination destination of the movement.
     * @return true if move is successful, otherwise false.
     */
    public boolean moveWorker(Player player, Location start, Location destination) {
        if (!sequenceHandler.isValidAction(player, WorkerAction.MOVE, start)) {
            System.out.println("invalid move action.");
            return false;
        }
        GameLogic currPlayerLogic = logics.get(player);
        if (!currPlayerLogic.isValidMove(board, start, destination) || !authorizedWorker(player, start)) {
            return false;
        }

        // movement is valid, apply actual move action.
        currPlayerLogic.move(board, start, destination);

        System.out.println("Move success. Destination: " + destination);

        // check winning case.
        if (currPlayerLogic.isWinningCase(board, destination)) {
            winner = player;
            System.out.println("Game over. Winner is " + winner.toString());
        }

        return true;
    }

    /**
     * helper method to identify if the worker on the selected start location belongs to the player.
     * @param player the player we want to know if he's the owner.
     * @param start the location holding the worker.
     * @return true if the worker belongs to the player.
     */
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
        if (!sequenceHandler.isValidAction(player, WorkerAction.BUILD, start)) {
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

    /**
     * skip the current action which is optional to the player.
     * the action could be move or build, according to the god card the player holds.
     * @param player the player performs the skip action.
     */
    public void skipAction(Player player) {
        GameLogic currPlayerLogic = logics.get(player);
        currPlayerLogic.skip();
    }

    /**
     * print the board.
     * for test purpose only.
     */
    public void printBoard() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Location currLoc = Location.get(i, j);
                if (board.getField(currLoc).isOccupied()){
                    System.out.print(board.getField(currLoc).getWorker().getPlayer().symbol());
                } else {
                    if (board.getField(currLoc).hasDome()) {
                        System.out.print("X");
                    } else {
                        int lvl = board.getField(currLoc).getHeight();
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

    public Player getWinner() {
        return winner;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public GameAction nextGameAction() {
        return sequenceHandler.nextGameAction();
    }

    public Player getCurrentPlayer() {
        return sequenceHandler.getCurrPlayer();
    }
}
