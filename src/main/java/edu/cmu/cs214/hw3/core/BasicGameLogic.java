package edu.cmu.cs214.hw3.core;

import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.playground.Field;
import edu.cmu.cs214.hw3.position.Location;

public class BasicGameLogic implements GameLogic {


    @Override
    public boolean isValidMove(Board board, Location start, Location destination) {
        if (!board.isCoordOnBoard(destination)) {
            System.out.println("destination is not on board.");
            return false;
        }

        if (board.isFieldOccupied(destination)) {
            System.out.println("destination is already occupied.");
            return false;
        }
        // check if the destination is too much higher.
        if (!Field.isMovable(
                board.getField(start),
                board.getField(destination))) {
            System.out.println("destination is unreachable");
            return false;
        }
        return true;
    }

    @Override
    public boolean isWinningCase(Board board, Location destination) {
         return board.isFieldMaxHeight(destination);
    }
}
