package edu.cmu.cs214.hw3.core;

import edu.cmu.cs214.hw3.player.Player;

public final class GameAction {

    private final Player player;
    private final WorkerAction action;

    public GameAction(Player player, WorkerAction action) {
        this.player = player;
        this.action = action;
    }

    public boolean matches(Player player, WorkerAction action) {
        return this.player == player && this.action == action;
    }
}
