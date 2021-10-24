package edu.cmu.cs214.hw3.listeners;

import edu.cmu.cs214.hw3.gameLogic.GameLogic;
import edu.cmu.cs214.hw3.player.Worker;

public interface EventListener {

    void castImpactAction(GameLogic gameLogic);

    void onMoveAction();

    void onBuildAction();

    void onNextActionEvent();
}
