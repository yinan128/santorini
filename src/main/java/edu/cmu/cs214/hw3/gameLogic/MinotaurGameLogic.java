package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.position.Location;

import java.util.Map;

/**
 * Game logic of Minotaur,
 * Your Worker may move into an opponent Worker’s space, if their Worker can be
 * forced one space straight backwards to an unoccupied space at any level.
 */
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
            return super.move(board, start, destination);
        }

        // case 2: field occupied by opponent worker.
        // check if the opponent worker can move back to prevState.
        Worker oppoWorker = board.getWorkerOnField(destination);
        Worker prevState = oppoWorker.getPrevState();
        if (prevState == null
                || isFieldOccupied(board, prevState.getLocation())
                || isFieldDomed(board, prevState.getLocation())) {
            return false;
        }
        // force move the opponent worker to previous location.
        super.forceMove(board, destination, prevState.getLocation());

        // move our worker to destination.
        return super.move(board, start, destination);
    }


    @Override
    public String toString() {
        return "minotaur logic";
    }
}
