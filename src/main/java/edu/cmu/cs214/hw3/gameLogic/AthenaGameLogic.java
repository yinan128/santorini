package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.listeners.EventListener;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.position.Location;

import java.util.Map;

public class AthenaGameLogic extends GameLogicDecorator {

    public AthenaGameLogic(GameLogic wrappee) {
        super(wrappee);
    }

    @Override
    public boolean isValidMove(Board board, Worker worker, Location destination) {
        return wrappee.isValidMove(board, worker, destination);
    }

    @Override
    public boolean move(Board board, Worker worker, Location destination) {
        boolean result = wrappee.move(board, worker, destination);
        if (result && board.deltaHeight(destination, worker.getLocation()) > 0) {
            // worker moved up.
            for (EventListener listener : listeners) {
                listener.castImpactAction(this);
            }
        }
        return result;
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
        for (Map.Entry<Player, GameLogic> entry : logics.entrySet()) {
            if (entry.getValue() == this) {
                continue;
            }
            logics.put(entry.getKey(), new BlockedGameLogic(entry.getValue()));
        }
    }
}
