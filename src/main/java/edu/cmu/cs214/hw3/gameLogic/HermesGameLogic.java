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


    public HermesGameLogic(GameLogic gameLogic) {
        super(gameLogic);
    }


    @Override
    public boolean move(Board board, Location start, Location destination) {
        if (board.deltaHeight(destination, start) == 0) {
            return super.forceMove(board, start, destination);
        }
        return super.move(board, start, destination);
    }

    @Override
    public void skip() {
        informNextAction();
    }
}
