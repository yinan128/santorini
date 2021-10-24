package edu.cmu.cs214.hw3.gameLogic;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.listeners.EventListener;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.position.Location;

import java.util.Map;

/**
 * The game logic of Athena
 * During opponent’s turn: If one of your Workers moved up on your last turn,
 * opponent Workers cannot move up this turn.
 */
public class AthenaGameLogic extends GameLogicDecorator {

    public AthenaGameLogic(GameLogic wrappee) {
        super(wrappee);
    }


    @Override
    public boolean move(Board board, Location start, Location destination) {
        // sequence here is problematic since the wrappee.move has already changed the game sequence.
        // But wrappee.move always return true.
        boolean result = super.move(board, start, destination);
        if (result && board.deltaHeight(destination, start) > 0) {
            // worker moved up.
            for (EventListener listener : getEventListeners()) {
                listener.castImpactAction(this);
            }
        }
        return result;
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
