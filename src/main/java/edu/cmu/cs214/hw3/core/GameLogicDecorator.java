package edu.cmu.cs214.hw3.core;

import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.playground.Field;
import edu.cmu.cs214.hw3.position.Location;

import java.util.List;

public abstract class GameLogicDecorator implements GameLogic {

    protected GameLogic wrappee;
    protected List<EventListener> listeners;


    public GameLogicDecorator(GameLogic gameLogic) {
        wrappee = gameLogic;
        for (EventListener l : wrappee.getEventListeners()) {
            subscribe(l);
        }
//        wrappee.getEventListeners().forEach(this::subscribe);
    }

    @Override
    public abstract boolean isValidMove(Board board, Worker worker, Location destination);

    @Override
    public abstract boolean move(Board board, Worker worker, Location destination);

    @Override
    public abstract boolean isWinningCase(Board board, Location destination);

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

    public boolean isCoordOnBoard(Board board, Location destination) {
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

    protected void forceMove(Board board, Worker worker, Location destination) {
        board.moveWorker(worker.getLocation(), destination);
    }
}
