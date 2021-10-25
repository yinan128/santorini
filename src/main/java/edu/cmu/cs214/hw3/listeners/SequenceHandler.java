package edu.cmu.cs214.hw3.listeners;

import edu.cmu.cs214.hw3.gameLogic.GameLogic;
import edu.cmu.cs214.hw3.player.Player;

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

    public boolean isValidAction(Player player, WorkerAction action) {
        return actions.get(currAction).matches(player, action);
    }


    @Override
    public void castImpactAction(GameLogic gameLogic) {

    }

    @Override
    public void onMoveAction() {

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
    }

    /**
     * go to the next action.
     */
    private void proceed() {
        currAction = (currAction + 1) % actions.size();
    }
}
