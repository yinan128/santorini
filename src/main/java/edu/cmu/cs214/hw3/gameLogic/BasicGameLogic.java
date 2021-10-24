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


    @Override
    public boolean isValidMove(Board board, Location start, Location destination) {
        if (!hasWorker(board, start)
                || !isLocationOnPerimeter(board, start, destination)
                || isFieldOccupied(board, destination)
                || isFieldDomed(board, destination)
                || !isDestReachable(board, start, destination)) {
            return false;
        }
        return true;
    }


    @Override
    public boolean move(Board board, Location start, Location destination) {
        board.moveWorker(start, destination);
        return true;
    }

    @Override
    public boolean isWinningCase(Board board, Location destination) {
        return board.isFieldBlockFull(destination);
    }


    @Override
    public boolean isBuildable(Board board, Location start, Location location) {
        if (!hasWorker(board, start)
                || !isLocationOnPerimeter(board, start, location)
                || isFieldOccupied(board, location)
                || isFieldDomed(board, location)) {
            return false;
        }
        return true;
    }


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

    private boolean hasWorker(Board board, Location start) {
        return board.hasWorker(start);
    }

    @Override
    public String toString() {
        return "basic game logic";
    }
}
