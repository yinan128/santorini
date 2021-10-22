package edu.cmu.cs214.hw3.player;

import edu.cmu.cs214.hw3.position.Direction;
import edu.cmu.cs214.hw3.position.Location;

import java.util.ArrayList;
import java.util.List;

public class Player {

    static final int WORKER_NUM = 2;
    private final List<Worker> workers;
    private final int playerNum;

    public Player(int i) {
        workers = new ArrayList<>();
        playerNum = i;
    }

    public boolean addWorker(Location location) {
        if (workers.size() == WORKER_NUM) {
            return false;
        }
        workers.add(new Worker(location, this));
        return true;
    }

    public Location getDestination(int workerIndex, Direction dir) {
        if (workerIndex > workers.size() - 1) {
            throw new IllegalArgumentException("worker index out of bound");
        }
        return workers.get(workerIndex).getDestination(dir);
    }

    public Location getWorkerLocation(int workerIndex) {
        return workers.get(workerIndex).getLocation();
    }

    @Override
    public String toString() {
        return "Player: " + playerNum;
    }

    /**
     * helper method to show the symbol of player on the board.
     * @return the symbol of the player on board.
     *         "A" represents player 0, "B" represents player 1.
     */
    public String symbol() {
        if (playerNum == 0) {
            return "A";
        }
        return "B";
    }

    public Worker getWorker(int workerIndex) {
        return workers.get(workerIndex);
    }

    public Worker getLastWorker() {
        if (workers.size() == 0) return null;
        return workers.get(workers.size() - 1);
    }
}
