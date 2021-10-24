package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.position.Location;

/**
 * Game logic of Artemis.
 * Your Worker may move one additional time, but not back to its initial space.
 * Skip is allowed.
 */
public class ArtemisGameLogic extends GameLogicDecorator {

    private Location initialSpace = null;

    public ArtemisGameLogic(GameLogic gameLogic) {
        super(gameLogic);
    }

    @Override
    public boolean isValidMove(Board board, Location start, Location destination) {
        if (initialSpace == null) {
            return super.isValidMove(board, start, destination);
        }
        return destination != initialSpace && super.isValidMove(board, start, destination);
    }

    @Override
    public boolean move(Board board, Location start, Location destination) {
        if (initialSpace != null) {
            // this is the second move.
            initialSpace = null;
            return super.move(board, start, destination);
        }
        // the first move won't update the game sequence since there is a second chance.
        return super.forceMove(board, start, destination);
    }


    @Override
    public void skip() {
        if (initialSpace == null) {
            throw new IllegalStateException("You cannot skip on the first move.");
        }
        initialSpace = null;
        informNextAction();
    }
}
