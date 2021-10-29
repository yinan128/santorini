package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.util.Location;

/**
 * Game logic of Hermes.
 * If your Workers do not move up or down, they may each move any number of
 * times (even zero), and then either builds.
 * Skip allowed.
 */
public class HermesGameLogic extends GameLogicDecorator {

    private boolean hasMoved = false;


    public HermesGameLogic(GameLogic gameLogic) {
        super(gameLogic);
    }


    @Override
    public boolean move(Board board, Location start, Location destination) {
        hasMoved = true;
        if (board.deltaHeight(destination, start) == 0) {
            return super.forceMove(board, start, destination);
        }
        hasMoved = false;
        return super.move(board, start, destination);
    }

    @Override
    public void skip() {
        if (!hasMoved) {
            throw new IllegalStateException("You cannot skip the first move.");
        }
        hasMoved = false;
        informNextAction();
    }
}
