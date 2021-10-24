package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.listeners.EventListener;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.position.Location;

import java.util.List;
import java.util.Map;

/**
 * Interface for game logic.
 */
public interface GameLogic {


    /**
     * game logic to check if the move action is valid.
     * @param board the board where movement happens.
     * @param start the starting location which holds the worker to be moved.
     * @param destination the destination of the movement.
     * @return true if the move is valid.
     */
    boolean isValidMove(Board board, Location start, Location destination);

    /**
     * the action to move the worker to a destination on board.
     * @param board the board where movement happens.
     * @param start the starting location which holds the worker to be moved.
     * @param destination the destination of the movement.
     * @return true if the move action is successful.
     */
    boolean move(Board board, Location start, Location destination);


    /**
     * move the worker to a destination on board without updating game sequence
     * @param board the board where movement happens.
     * @param start the starting location which holds the worker to be moved.
     * @param destination the destination of the movement.
     * @return true if the move action is successful.
     */
    boolean forceMove(Board board, Location start, Location destination);

    /**
     * check if the previous successful move leads to a winning case.
     * @param board the board where movement happens.
     * @param destination the destination of the previous successful move.
     * @return true if the previous move generates a winner.
     */
    boolean isWinningCase(Board board, Location destination);

    /**
     * check if the given location is buildable
     * @param board the board where the game is played.
     * @param start the starting location which holds the worker to build.
     * @param location building location
     * @return true if tower block can be built on the given location.
     */
    boolean isBuildable(Board board, Location start, Location location);

    /**
     * the action to build on the given location by the worker.
     * @param board the board where the game is played.
     * @param location building location
     * @return true if build is successful.
     */
    boolean build(Board board,  Location location);

    /**
     * Build on the given location by the worker without updating the game sequence.
     * @param board the board where the game is played.
     * @param location building location
     * @return true if build is successful.
     */
    boolean forceBuild(Board board,  Location location);

    /**
     * the action to skip an optional action.
     */
    void skip();

    /**
     * subscribe a event listener.
     * @param listener a new event listener.
     */
    void subscribe(EventListener listener);

    List<EventListener> getEventListeners();

    /**
     * cast god power onto the game logic.
     * @param logics the game logics for all players in the game.
     */
    void castImpact(Map<Player, GameLogic> logics);

    GameLogic getWrappee();


    /**
     * the action to place the worker at a certain location at the start of game.
     * @param worker worker to be placed.
     * @param location the location where the worker is to be placed.
     * @return true if placement is successful.
     */
    boolean placeWorker(Board board, Worker worker, Location location);

    /**
     * notify the event listeners of the move action.
     */
    void informOnMoveAction();

    /**
     * notify the event listeners of the build action.
     */
    void informOnBuildAction();

    /**
     * notify the event listeners of the currently finished action.
     */
    void informNextAction();

}
