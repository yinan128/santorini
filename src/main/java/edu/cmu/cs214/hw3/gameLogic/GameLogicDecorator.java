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
    protected List<EventListener> listeners;


    public GameLogicDecorator(GameLogic gameLogic) {
        wrappee = gameLogic;
        listeners = wrappee.getEventListeners();
    }

    @Override
    public boolean isValidMove(Board board, Location start, Location destination) {
        return wrappee.isValidMove(board, start, destination);
    }

    @Override
    public boolean move(Board board, Location start, Location destination) {
        informOnMoveAction();
        return wrappee.move(board, start, destination);
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
        informOnBuildAction();
        return wrappee.build(board, location);
    }

    @Override
    public void skip() {
        throw new UnsupportedOperationException();
    }


    @Override
    public void subscribe(EventListener listener) {
        listeners.add(listener);
    }

    @Override
    public List<EventListener> getEventListeners() {
        return listeners;
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

    protected void informOnMoveAction() {
        for (EventListener listener: listeners) {
            listener.onMoveAction();
        }
    }

    protected void informOnBuildAction() {
        for (EventListener listener: listeners) {
            listener.onBuildAction();
        }
    }

    protected void informOnSkipAction() {
        for (EventListener listener: listeners) {
            listener.onSkipAction();
        }
    }

}
