package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.position.Location;

import java.util.Map;

public class MinotaurGameLogic extends GameLogicDecorator {

    public MinotaurGameLogic(GameLogic gameLogic) {
        super(gameLogic);
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
        // For Minotaur, we still need basic validation on destination.
        if (!isLocationOnBoard(board, destination)
                || isFieldDomed(board, destination)) {
            return false;
        }
        if (!isFieldOccupied(board, destination)) {
            return isDestReachable(board, worker.getLocation(), destination);
        }
        // check if the occupied worker has the same owner.
        return !Worker.fromSamePlayer(board.getWorkerOnField(destination), worker);
    }

    @Override
    public boolean move(Board board, Worker worker, Location destination) {
        // case 1: field empty
        if (!isFieldOccupied(board, destination)) {
            return wrappee.move(board, worker, destination);
        }

        // case 2: field occupied
        // check if the opponent worker can move back to prevState.
        Worker oppoWorker = board.getWorkerOnField(destination);
        Worker prevState = oppoWorker.getPrevState();
        if (prevState == null
                || isFieldOccupied(board, prevState.getLocation())
                || isFieldDomed(board, prevState.getLocation())) {
            return false;
        }
        // force move the opponent worker to previous location.
        wrappee.move(board, oppoWorker, prevState.getLocation());
        //todo which is better?
//        forceMove(board, oppoWorker, prevState.getLocation());

        // move our worker to destination.
        return wrappee.move(board, worker, destination);
    }

    @Override
    public boolean isWinningCase(Board board, Location destination) {
        return wrappee.isWinningCase(board, destination);
    }

    @Override
    public boolean isBuildable(Board board, Worker worker, Location location) {
        return wrappee.isBuildable(board, worker, location);
    }

    @Override
    public boolean build(Board board, Location location) {
        return wrappee.build(board, location);
    }

    @Override
    public void castImpact(Map<Player, GameLogic> logics) {
    }

}
