package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.position.Location;

/**
 * Game logic of Atlas.
 * Your Worker may build a dome at any level.
 * (UI hint: you can implement this in the user interface similar to Hephaestus,
 * giving the player a second optional build action;
 * this build action is interpreted as building a dome)
 */
public class AtlasGameLogic extends GameLogicDecorator {

    private Location prevBuild = null;

    public AtlasGameLogic(GameLogic gameLogic) {
        super(gameLogic);
    }

    @Override
    public boolean isBuildable(Board board, Location start, Location location) {
        if (prevBuild == null) return super.isBuildable(board, start, location);
        return location == prevBuild && super.isBuildable(board, start, location);
    }

    @Override
    public boolean build(Board board, Location location) {
        if (prevBuild == null) {
            prevBuild = location;
            return super.forceBuild(board, location);
        }
        prevBuild = null;
        informOnBuildAction();
        informNextAction();
        board.removeOneBlock(location);
        return board.buildDome(location);
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
