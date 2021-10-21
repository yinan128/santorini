package edu.cmu.cs214.hw3.core;

import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.position.Location;

public interface GameLogic {

    boolean isValidMove(Board board, Location start, Location destination);

    boolean isWinningCase(Board board, Location destination);
}
