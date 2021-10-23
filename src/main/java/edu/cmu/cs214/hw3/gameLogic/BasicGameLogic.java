package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.listeners.EventListener;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.playground.Field;
import edu.cmu.cs214.hw3.position.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BasicGameLogic implements GameLogic {

    private final List<EventListener> listeners;

    public BasicGameLogic() {
        listeners = new ArrayList<>();
    }

    /**
     * basic game logic to check if the move action is valid.
     * @param board the board where movement happens.
     * @param worker the worker to be moved.
     * @param destination the destination of the movement.
     * @return true if the move is valid.
     */
    @Override
    public boolean isValidMove(Board board, Worker worker, Location destination) {
        if (!isLocationOnPerimeter(board, worker.getLocation(), destination)
                || isFieldOccupied(board, destination)
                || isFieldDomed(board, destination)
                || !isDestReachable(board, worker.getLocation(), destination)) {
            return false;
        }
        return true;
    }

    /**
     * the action to move the worker to a destination on board.
     * @param board the board where movement happens.
     * @param worker the worker to be moved.
     * @param destination the destination of the movement.
     * @return true if the move action is successful.
     */
    @Override
    public boolean move(Board board, Worker worker, Location destination) {
        Location start = worker.getLocation();
        board.moveWorker(start, destination);
        for (EventListener listener : listeners) {
            listener.onMoveAction(board.getWorkerOnField(destination));
        }
//        listeners.forEach(listener -> listener.onMoveAction(board.getWorkerOnField(destination)));
        return true;
    }

    @Override
    public boolean isWinningCase(Board board, Location destination) {
        return board.isFieldBlockFull(destination);
    }

    /**
     * check if the given location is buildable
     *
     * @param board    the board where the game is played.
     * @param worker   the construction worker.
     * @param location building location
     * @return true if tower block can be built on the given location.
     */
    @Override
    public boolean isBuildable(Board board, Worker worker, Location location) {
        System.out.println("worker loc:" + worker.getLocation());
        if (!isLocationOnPerimeter(board, worker.getLocation(), location)
                || isFieldOccupied(board, location)
                || isFieldDomed(board, location)) {
            return false;
        }
        return true;
    }

    /**
     * the action to build on the given location by the worker.
     *
     * @param board         the board where the game is played.
     * @param location      building location
     * @return true if build is successful.
     */
    @Override
    public boolean build(Board board, Location location) {
        return board.buildOn(location);
    }

    @Override
    public void subscribe(EventListener listener) {
        listeners.add(listener);
    }

    @Override
    public List<EventListener> getEventListeners() {
        return listeners;
    }

    @Override
    public void castImpact(Map<Player, GameLogic> logics) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GameLogic getWrappee() {
        throw new UnsupportedOperationException();
    }

    /**
     * the action to place the worker at a certain location at the start of game.
     *
     * @param worker   worker to be placed.
     * @param location the location where the worker is to be placed.
     * @return true if placement is successful.
     */
    @Override
    public boolean placeWorker(Board board, Worker worker, Location location) {
        return board.placeWorker(worker, location);
    }

    /**
     * check if a given location is on the perimeter of the start location.
     * @param board the board where the game is played.
     * @param start the start location.
     * @param location the location we want to know if it is on the perimeter of the start location.
     * @return true if the location is on the perimeter of the start location.
     */
    private boolean isLocationOnPerimeter(Board board, Location start, Location location) {
        if (!board.getLocationPerimeter(start).contains(location)) {
            System.out.println("destination is not on the perimeter.");
            return false;
        }
        return true;
    }


    private boolean isLocationOnBoard(Board board, Location destination) {
        return board.isCoordOnBoard(destination);
    }

    private boolean isFieldOccupied(Board board, Location destination) {
        return board.isFieldOccupied(destination);
    }

    private boolean isDestReachable(Board board, Location start, Location destination) {
        return Field.isMovable(
                board.getField(start),
                board.getField(destination));
    }

    private boolean isFieldDomed(Board board, Location destination) {
        return board.isFieldDomed(destination);
    }



}
