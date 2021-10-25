package edu.cmu.cs214.hw3.listeners;

import edu.cmu.cs214.hw3.player.Player;

/**
 * A class to represent a game action in the game flow.
 */
public final class GameAction {

    private final Player player;
    private final WorkerAction action;

    public GameAction(Player player, WorkerAction action) {
        this.player = player;
        this.action = action;
    }

    /**
     * Identify if the parameters match the current instance.
     * @param player the player who wants to make an action.
     * @param action the action player wants to make.
     * @return true if they match.
     */
    public boolean matches(Player player, WorkerAction action) {
        return this.player == player && this.action == action;
    }
}
