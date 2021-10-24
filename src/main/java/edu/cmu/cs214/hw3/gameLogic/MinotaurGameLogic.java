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


    @Override
    public boolean isValidMove(Board board, Location start, Location destination) {
        // For Minotaur, we still need basic validation on destination.
        if (!isLocationOnBoard(board, destination)
                || isFieldDomed(board, destination)) {
            return false;
        }
        if (!isFieldOccupied(board, destination)) {
            return isDestReachable(board, start, destination);
        }
        // check if the occupied worker has the same owner.
        return !Worker.fromSamePlayer(board.getWorkerOnField(destination), board.getWorkerOnField(start));
    }

    @Override
    public boolean move(Board board, Location start, Location destination) {
        // case 1: field empty
        if (!isFieldOccupied(board, destination)) {
            return wrappee.move(board, start, destination);
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
        wrappee.move(board, destination, prevState.getLocation());
        //todo which is better?
//        forceMove(board, oppoWorker, prevState.getLocation());

        // move our worker to destination.
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
        return wrappee.build(board, location);
    }

    @Override
    public void castImpact(Map<Player, GameLogic> logics) {
    }

    @Override
    public String toString() {
        return "minotaur logic";
    }
}
