package edu.cmu.cs214.hw3.core;

import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.position.Location;

import java.util.List;
import java.util.Map;

public interface GameLogic {


    /**
     * game logic to check if the move action is valid.
     * @param board the board where movement happens.
     * @param worker the worker to be moved.
     * @param destination the destination of the movement.
     * @return true if the move is valid.
     */
    boolean isValidMove(Board board, Worker worker, Location destination);

    /**
     * the action to move the worker to a destination on board.
     * @param board the board where movement happens.
     * @param worker the worker to be moved.
     * @param destination the destination of the movement.
     * @return true if the move action is successful.
     */
    boolean move(Board board, Worker worker, Location destination);

    /**
     * check if the previous successful move leads to a winning case.
     * @param board the board where movement happens.
     * @param destination the destination of the previous successful move.
     * @return true if the previous move generates a winner.
     */
    boolean isWinningCase(Board board, Location destination);

    void subscribe(EventListener listener);

    List<EventListener> getEventListeners();

    void castImplact(Map<Player, GameLogic> logics);

    GameLogic getWrappee();
}
