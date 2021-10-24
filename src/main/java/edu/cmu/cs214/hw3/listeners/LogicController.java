package edu.cmu.cs214.hw3.listeners;

import edu.cmu.cs214.hw3.gameLogic.GameLogic;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;

import java.util.Map;

public class LogicController implements EventListener {

    Map<Player, GameLogic> logics;

    public LogicController(Map<Player, GameLogic> logics) {
        this.logics = logics;
    }

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
