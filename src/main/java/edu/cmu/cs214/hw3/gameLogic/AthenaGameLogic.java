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
    public boolean isValidMove(Board board, Location start, Location destination) {
        return wrappee.isValidMove(board, start, destination);
    }

    @Override
    public boolean move(Board board, Location start, Location destination) {
        boolean result = wrappee.move(board, start, destination);
        if (result && board.deltaHeight(destination, start) > 0) {
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
    public boolean isBuildable(Board board, Location start, Location location) {
        return wrappee.isBuildable(board, start, location);
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
            System.out.println(entry.getValue());
            logics.put(entry.getKey(), new BlockedGameLogic(entry.getValue()));
        }
    }

    @Override
    public String toString() {
        return "athena logic";
    }
}
