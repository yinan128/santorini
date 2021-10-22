package edu.cmu.cs214.hw3.core;

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
        // check if destination is on board.
        if (!board.isCoordOnBoard(destination)) {
            System.out.println("destination is not on board.");
            return false;
        }

        // check if the destination field is occupied.
        if (board.isFieldOccupied(destination)) {
            System.out.println("destination is already occupied by another worker.");
            return false;
        }

        // check if the destination field has a tower with dome.
        if (board.isFieldDomed(destination)) {
            System.out.println("destination field is completely built");
            return false;
        }

        // check if the destination is 2+ levels higher.
        Location start = worker.getLocation();
        if (!Field.isMovable(
                board.getField(start),
                board.getField(destination))) {
            System.out.println("destination is 2 levels higher than start position");
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
        return true;
    }

    @Override
    public boolean isWinningCase(Board board, Location destination) {
        return board.isFieldBlockFull(destination);
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
    public void castImplact(Map<Player, GameLogic> logics) {
        throw new UnsupportedOperationException();
    }

    @Override
    public GameLogic getWrappee() {
        throw new UnsupportedOperationException();
    }


}
