package edu.cmu.cs214.hw3.listeners;

import edu.cmu.cs214.hw3.gameLogic.GameLogic;
import edu.cmu.cs214.hw3.player.Player;

import java.util.Map;

public class LogicManager implements EventListener {

    Map<Player, GameLogic> logics;

    public LogicManager(Map<Player, GameLogic> logics) {
        this.logics = logics;
    }

    @Override
    public void castImpactAction(GameLogic gameLogic) {
        gameLogic.castImpact(logics);
    }
}
