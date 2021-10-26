package edu.cmu.cs214.hw3.listeners;

import edu.cmu.cs214.hw3.gameLogic.GameLogic;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.util.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to handle the action sequence in the game.
 */
public class SequenceHandler implements EventListener {

    private List<GameAction> actions;

    /**
     * pointer to the current valid action.
     */
    private int currAction;

    public SequenceHandler(List<Player> players) {
        actions = new ArrayList<>();
        for (Player player : players) {
            actions.add(new GameAction(player, WorkerAction.MOVE));
            actions.add(new GameAction(player, WorkerAction.BUILD));
        }
        currAction = 0;
    }

    /**
     * Identify if the player want to apply a certain action on the worker located on the designated location.
     * @param player the player performs an action.
     * @param action the action to be taken.
     * @param location the location holding the worker to be manipulated.
     * @return true if the player can do so.
     */
    public boolean isValidAction(Player player, WorkerAction action, Location location) {
        return actions.get(currAction).matches(player, action, location);
    }


    @Override
    public void castImpactAction(GameLogic gameLogic) {

    }

    @Override
    public void onMoveAction(Location location) {
        // set the valid location for the move action since there can be optional moves incoming.
        // also set the valid location for the build action.
        for (GameAction action : actions) {
            if (action.getPlayer() == actions.get(currAction).getPlayer()) {
                action.setValidLocation(location);
            }
        }
    }

    @Override
    public void onBuildAction() {

    }

    /**
     * proceed to the next action.
     */
    @Override
    public void onNextActionEvent() {
        proceed();
        // when all the players have made actions in a round, reset the valid locations.
        if (currAction == 0) {
            actions.forEach(GameAction::resetValidLocation);
        }
    }

    /**
     * go to the next action.
     */
    private void proceed() {
        currAction = (currAction + 1) % actions.size();
    }
}
