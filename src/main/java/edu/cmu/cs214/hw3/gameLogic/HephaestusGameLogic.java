package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.position.Location;

/**
 * Game logic of Hephaestus.
 * Your Worker may build one additional block (not dome) on top of your first block.
 * Skip is allowed.
 */
public class HephaestusGameLogic extends GameLogicDecorator {

    private Location prevBuild = null;

    public HephaestusGameLogic(GameLogic gameLogic) {
        super(gameLogic);
    }

    @Override
    public boolean isBuildable(Board board, Location start, Location location) {
        if (prevBuild == null) return super.isBuildable(board, start, location);
        return location == prevBuild
                && !board.isFieldBlockFull(location)
                && super.isBuildable(board, start, location);
    }

    @Override
    public boolean build(Board board, Location location) {
        if (prevBuild == null) {
            prevBuild = location;
            return super.forceBuild(board, location);
        }
        prevBuild = null;
        return super.build(board, location);
    }

    @Override
    public void skip() {
        if (prevBuild == null) {
            throw new IllegalStateException("You cannot skip the first build.");
        }
        prevBuild = null;
        informNextAction();
    }
}
