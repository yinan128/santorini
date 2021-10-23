package edu.cmu.cs214.hw3.player;

import edu.cmu.cs214.hw3.position.Direction;
import edu.cmu.cs214.hw3.position.Location;

import java.util.ArrayList;
import java.util.List;

public class Player {

    static final int WORKER_NUM = 2;
    private final int playerNum;
    private int currWorkerNum;

    public Player(int i) {
        playerNum = i;
        currWorkerNum = 0;
    }


//    public Location getWorkerLocation(int workerIndex) {
//        return workers.get(workerIndex).getLocation();
//    }

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

//    public Worker getWorker(int workerIndex) {
//        return workers.get(workerIndex);
//    }
//
//    public Worker initWorker(Location location) {
//        return new Worker(location, this);
//    }
//
    public boolean addWorker(Worker worker) {
//        if (workers.size() == WORKER_NUM) return false;
//        workers.add(worker);
//        return true;
        currWorkerNum++;
        return true;
    }

    public boolean workerFull() {
        return currWorkerNum == WORKER_NUM;
    }
}
