package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.listeners.EventListener;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.playground.Field;
import edu.cmu.cs214.hw3.position.Location;

import java.util.List;

public abstract class GameLogicDecorator implements GameLogic {

    protected GameLogic wrappee;
    protected List<EventListener> listeners;


    public GameLogicDecorator(GameLogic gameLogic) {
        wrappee = gameLogic;
        listeners = wrappee.getEventListeners();
    }

    @Override
    public abstract boolean isValidMove(Board board, Location start, Location destination);

    @Override
    public abstract boolean move(Board board, Location start, Location destination);

    @Override
    public abstract boolean isWinningCase(Board board, Location destination);

    @Override
    public abstract boolean isBuildable(Board board, Location start, Location location);

    @Override
    public abstract boolean build(Board board, Location location);




    @Override
    public void subscribe(EventListener listener) {
        listeners.add(listener);
    }

    @Override
    public List<EventListener> getEventListeners() {
        return listeners;
    }

    @Override
    public GameLogic getWrappee() {
        return wrappee;
    }


    @Override
    public boolean placeWorker(Board board, Worker worker, Location location) {
        return board.placeWorker(worker, location);
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


//    protected void forceMove(Board board, Worker worker, Location destination) {
//        board.moveWorker(worker.getLocation(), destination);
//    }
}
