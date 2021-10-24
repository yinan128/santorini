package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.position.Location;

public class DemeterGameLogic extends GameLogicDecorator {

    private Location lastBuild = null;

    public DemeterGameLogic(GameLogic gameLogic) {
        super(gameLogic);
    }

    @Override
    public boolean isBuildable(Board board, Location start, Location location) {
        if (lastBuild == null) {
            return wrappee.isBuildable(board, start, location);
        }
        return location != lastBuild && wrappee.isBuildable(board, start, location);
    }

    @Override
    public boolean build(Board board, Location location) {
        if (lastBuild != null) {
            informOnBuildAction();
            lastBuild = null;
        }
        return wrappee.build(board, location);
    }

    @Override
    public void skip() {
        informOnSkipAction();
    }
}
