package edu.cmu.cs214.hw3.core;

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
        if (board.deltaHeight(destination, worker.getLocation()) > 0) {
            // worker moved up.
            for (EventListener listener : listeners) {
                listener.castImpactAction(this);
            }
        }
        return true;
    }

    @Override
    public boolean isWinningCase(Board board, Location destination) {
        return false;
    }

    @Override
    public void castImplact(Map<Player, GameLogic> logics) {
        for (Map.Entry<Player, GameLogic> entry : logics.entrySet()) {
            if (entry.getValue() == this) {
                continue;
            }
            logics.put(entry.getKey(), new BlockedGameLogic(entry.getValue()));
        }
    }
}
