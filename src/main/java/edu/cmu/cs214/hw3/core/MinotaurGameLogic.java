package edu.cmu.cs214.hw3.core;

import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.position.Location;

public class MinoaurGameLogic extends GameLogicDecorator {

    public MinoaurGameLogic(GameLogic gameLogic) {
        super(gameLogic);
    }

    @Override
    public boolean isValidMove(Board board, Worker worker, Location destination) {
        return false;
    }

    @Override
    public boolean move(Board board, Worker worker, Location destination) {
        return false;
    }

    @Override
    public boolean isWinningCase(Board board, Location destination) {
        return false;
    }
}
