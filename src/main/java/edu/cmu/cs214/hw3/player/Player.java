package edu.cmu.cs214.hw3.player;

public class Player {

    static final int WORKER_NUM = 2;
    private final int playerNum;
    private int currWorkerNum;

    public Player(int i) {
        playerNum = i;
        currWorkerNum = 0;
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

    public void addWorker() {
        currWorkerNum++;
    }

    public boolean workerFull() {
        return currWorkerNum == WORKER_NUM;
    }
}
