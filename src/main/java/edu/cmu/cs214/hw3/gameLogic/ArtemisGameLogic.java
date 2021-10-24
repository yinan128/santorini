package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.position.Location;

import java.util.Map;

/**
 * Game logic of Artemis.
 * Your Worker may move one additional time, but not back to its initial space.
 */
public class ArtemisGameLogic extends GameLogicDecorator {

    private Location initialSpace = null;

    public ArtemisGameLogic(GameLogic gameLogic) {
        super(gameLogic);
    }

    @Override
    public boolean isValidMove(Board board, Location start, Location destination) {
        if (initialSpace == null) {
            return wrappee.isValidMove(board, start, destination);
        }
        return destination != initialSpace && wrappee.isValidMove(board, start, destination);
    }

    @Override
    public boolean move(Board board, Location start, Location destination) {
        if (initialSpace != null) {
            // only update the game sequence if this is the second move
            informOnMoveAction();
            initialSpace = null;
        }
        return wrappee.move(board, start, destination);
    }


    @Override
    public void skip() {
        informOnSkipAction();
    }
}
