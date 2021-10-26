package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.util.Location;

/**
 * Game logic of Apollo.
 * Your Worker may move into an opponent Worker’s space by forcing their Worker to
 * the space yours just vacated.
 */
public class ApolloGameLogic extends GameLogicDecorator {

    public ApolloGameLogic(GameLogic gameLogic) {
        super(gameLogic);
    }


    @Override
    public boolean isValidMove(Board board, Location start, Location destination) {
        // For Apollo, we still need basic validation on destination.
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
//            return wrappee.move(board, start, destination);
        }

        // case 2: field occupied. Swap the location with the opponent worker.
        // 1.force move the opponent worker to our location.
        board.moveWorker(destination, start);
        // 2.move our worker to destination.
        return super.move(board, start, destination);
    }
}
