package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.position.Location;

import java.util.Map;

public class BlockedGameLogic extends GameLogicDecorator {

    public BlockedGameLogic(GameLogic wrappee) {
        super(wrappee);
    }

    @Override
    public boolean isValidMove(Board board, Worker worker, Location destination) {
        // additional check for move up action.
        if (board.deltaHeight(destination, worker.getLocation()) > 0) {
            return false;
        }
        return wrappee.isValidMove(board, worker, destination);
    }

    @Override
    public boolean move(Board board, Worker worker, Location destination) {
        boolean success =  wrappee.move(board, worker, destination);
        if (success) {
            listeners.forEach(l -> l.castImpactAction(this));
        }
        return success;
    }

    @Override
    public boolean isWinningCase(Board board, Location destination) {
        return wrappee.isWinningCase(board, destination);
    }

    @Override
    public boolean isBuildable(Board board, Worker worker, Location location) {
        return wrappee.isBuildable(board, worker, location);
    }

    @Override
    public boolean build(Board board, Location location) {
        return wrappee.build(board, location);
    }

    @Override
    public void castImpact(Map<Player, GameLogic> logics) {
        // the impact after a successful move is to remove the decorator.
        for (Map.Entry<Player, GameLogic> entry : logics.entrySet()) {
            if (entry.getValue() == this) {
                logics.put(entry.getKey(), entry.getValue().getWrappee());
                return;
            }
        }
    }
}
