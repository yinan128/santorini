package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.position.Location;

import java.util.Map;

public class BlockedGameLogic extends GameLogicDecorator {

    public BlockedGameLogic(GameLogic wrappee) {
        super(wrappee);
    }

    @Override
    public boolean isValidMove(Board board, Location start, Location destination) {
        // additional check for move up action.
        if (board.deltaHeight(destination, start) > 0) {
            System.out.println("cannot move up due to Athena power.");
            return false;
        }
        return wrappee.isValidMove(board, start, destination);
    }

    @Override
    public boolean move(Board board, Location start, Location destination) {
        boolean success =  wrappee.move(board, start, destination);
        if (success) {
            listeners.forEach(l -> l.castImpactAction(this));
            informOnMoveAction();
        }
        return success;
    }

    @Override
    public void castImpact(Map<Player, GameLogic> logics) {
        // the impact after a successful move is to remove the decorator.
        for (Map.Entry<Player, GameLogic> entry : logics.entrySet()) {
            if (entry.getValue() == this) {
                GameLogic prevLogic = entry.getValue().getWrappee();
                logics.put(entry.getKey(), prevLogic);
                return;
            }
        }
    }

    @Override
    public String toString() {
        return "blocked logic -> " + getWrappee().toString();
    }
}