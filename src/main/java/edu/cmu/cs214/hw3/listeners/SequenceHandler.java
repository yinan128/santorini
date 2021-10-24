package edu.cmu.cs214.hw3.listeners;

import edu.cmu.cs214.hw3.core.GameAction;
import edu.cmu.cs214.hw3.core.WorkerAction;
import edu.cmu.cs214.hw3.gameLogic.GameLogic;
import edu.cmu.cs214.hw3.listeners.EventListener;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;

import java.util.ArrayList;
import java.util.List;

public class SequenceHandler implements EventListener {

    private List<GameAction> actions;
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
