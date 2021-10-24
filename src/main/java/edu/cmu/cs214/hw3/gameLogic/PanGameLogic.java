package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.position.Location;

import java.util.Map;

/**
 * Game logic of Pan.
 * You also win if your Worker moves down two or more levels
 */
public class PanGameLogic extends GameLogicDecorator {


    public PanGameLogic(GameLogic gameLogic) {
        super(gameLogic);
    }

    @Override
    public boolean isWinningCase(Board board, Location destination) {
        if (super.isWinningCase(board, destination)) return true;
        // the player also wins if his worker moves down two or more levels.
        return Math.abs(board.workerLevelChange(destination)) > 1;
    }

}
