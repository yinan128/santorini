package edu.cmu.cs214.hw3.listeners;

import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.util.Location;

import java.util.Locale;

/**
 * A class to represent a valid game action in the game flow.
 */
public final class GameAction {

    private final Player player;
    private final WorkerAction action;

    /**
     * the location of the worker that is eligible to be selected to do actions.
     */
    private Location location;

    public GameAction(Player player, WorkerAction action) {
        this.player = player;
        this.action = action;
        this.location = null;
    }

    public void setValidLocation(Location location) {
        this.location = location;
    }

    public void resetValidLocation() {
        this.location = null;
    }

    /**
     * Identify if the parameters match the current instance.
     * @param player the player who wants to make an action.
     * @param action the action player wants to make.
     * @return true if they match.
     */
    public boolean matches(Player player, WorkerAction action, Location location) {
        boolean locationMatch;
        if (this.location == null) {
            locationMatch = true;
        } else {
            locationMatch = (this.location == location);
        }
        return locationMatch && this.player == player && this.action == action;
    }

    public Player getPlayer() {
        return player;
    }

    public String getActionName() {
        return action.toString().toLowerCase();
    }
}
