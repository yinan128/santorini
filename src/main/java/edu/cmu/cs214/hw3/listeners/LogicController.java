package edu.cmu.cs214.hw3.listeners;

import edu.cmu.cs214.hw3.gameLogic.GameLogic;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;

import java.util.Map;

/**
 * Controller of the game logics of all the players in the current game.
 */
public class LogicController implements EventListener {

    Map<Player, GameLogic> logics;

    public LogicController(Map<Player, GameLogic> logics) {
        this.logics = logics;
    }

    /**
     * let the game logic which sent the notification to modify the game rules.
     * @param gameLogic the game logic which send the event notification.
     */
    @Override
    public void castImpactAction(GameLogic gameLogic) {
        gameLogic.castImpact(logics);
    }

    @Override
    public void onMoveAction() {
    }

    @Override
    public void onBuildAction() {
    }

    @Override
    public void onNextActionEvent() {
    }

    @Override
    public String toString() {
        return "LogicManager";
    }
}
