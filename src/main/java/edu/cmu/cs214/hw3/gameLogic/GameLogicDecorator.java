package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.listeners.EventListener;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.playground.Field;
import edu.cmu.cs214.hw3.position.Location;

import java.util.List;
import java.util.Map;

public class GameLogicDecorator implements GameLogic {

    protected GameLogic wrappee;


    public GameLogicDecorator(GameLogic gameLogic) {
        wrappee = gameLogic;
    }

    @Override
    public boolean isValidMove(Board board, Location start, Location destination) {
        return wrappee.isValidMove(board, start, destination);
    }

    @Override
    public boolean move(Board board, Location start, Location destination) {
        return wrappee.move(board, start, destination);
    }

    /**
     * move the worker to a destination on board without updating game sequence
     *
     * @param board       the board where movement happens.
     * @param start       the starting location which holds the worker to be moved.
     * @param destination the destination of the movement.
     * @return true if the move action is successful.
     */
    @Override
    public boolean forceMove(Board board, Location start, Location destination) {
        board.moveWorker(start, destination);
        informOnMoveAction();
        return true;
    }

    @Override
    public boolean isWinningCase(Board board, Location destination) {
        return wrappee.isWinningCase(board, destination);
    }

    @Override
    public boolean isBuildable(Board board, Location start, Location location) {
        return wrappee.isBuildable(board, start, location);
    }

    @Override
    public boolean build(Board board, Location location) {
        return wrappee.build(board, location);
    }

    /**
     * Build on the given location by the worker without updating the game sequence.
     *
     * @param board    the board where the game is played.
     * @param location building location
     * @return true if build is successful.
     */
    @Override
    public boolean forceBuild(Board board, Location location) {
        informOnBuildAction();
        return board.buildOn(location);
    }

    @Override
    public void skip() {
        throw new UnsupportedOperationException();
    }


    @Override
    public void subscribe(EventListener listener) {
        wrappee.subscribe(listener);
    }

    @Override
    public List<EventListener> getEventListeners() {
        return wrappee.getEventListeners();
    }

    /**
     * cast god power onto the game logic.
     *
     * @param logics the game logics for all players in the game.
     */
    @Override
    public void castImpact(Map<Player, GameLogic> logics) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GameLogic getWrappee() {
        return wrappee;
    }


    @Override
    public boolean placeWorker(Board board, Worker worker, Location location) {
        return board.placeWorker(worker, location);
    }

    @Override
    public void informOnMoveAction() {
        wrappee.informOnMoveAction();
    }

    @Override
    public void informOnBuildAction() {
        wrappee.informOnBuildAction();
    }

    @Override
    public void informNextAction() {
        wrappee.informNextAction();
    }

    public boolean isLocationOnPerimeter(Board board, Location start, Location destination) {
        if (!board.getLocationPerimeter(start).contains(destination)) {
            return false;
        }
        return true;
    }

    public boolean isLocationOnBoard(Board board, Location destination) {
        return board.isCoordOnBoard(destination);
    }

    public boolean isFieldOccupied(Board board, Location destination) {
        return board.isFieldOccupied(destination);
    }

    public boolean isDestReachable(Board board, Location start, Location destination) {
        return Field.isMovable(
                board.getField(start),
                board.getField(destination));
    }

    public boolean isFieldDomed(Board board, Location destination) {
        return board.isFieldDomed(destination);
    }



}
