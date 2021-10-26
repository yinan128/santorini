package edu.cmu.cs214.hw3.listeners;

import edu.cmu.cs214.hw3.gameLogic.GameLogic;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.util.Location;

/**
 * Event listener interface to handle different events from the game.
 */
public interface EventListener {

    /**
     * when a god card has cast its power on the game rules.
     * @param gameLogic the game logic which send the event notification.
     */
    void castImpactAction(GameLogic gameLogic);

    /**
     * when player moved his worker.
     */
    void onMoveAction(Location location);

    /**
     * when player made a build action.
     */
    void onBuildAction();

    /**
     * when the player finished his current action and wanted to proceed the game.
     */
    void onNextActionEvent();
}
