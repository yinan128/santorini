package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.util.Location;

/**
 * Game logic of Demeter.
 * Your Worker may build one additional time, but not on the same space.
 * Skip is allowed.
 */
public class DemeterGameLogic extends GameLogicDecorator {

    private Location lastBuild = null;

    public DemeterGameLogic(GameLogic gameLogic) {
        super(gameLogic);
    }

    @Override
    public boolean isBuildable(Board board, Location start, Location location) {
        if (lastBuild == null) {
            return super.isBuildable(board, start, location);
        }
        return location != lastBuild && super.isBuildable(board, start, location);
    }

    @Override
    public boolean build(Board board, Location location) {
        if (lastBuild != null) {
            lastBuild = null;
            return super.build(board, location);
        }
        lastBuild = location;
        return super.forceBuild(board, location);
    }

    @Override
    public void skip() {
        if (lastBuild == null) {
            throw new IllegalStateException("You cannot skip on the first build.");
        }
        lastBuild = null;
        informNextAction();
    }
}
