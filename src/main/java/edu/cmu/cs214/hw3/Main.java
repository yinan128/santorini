package edu.cmu.cs214.hw3;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.position.Direction;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final int CORNER = 4;

    public static void main(String[] args) {
        Board board = new Board();
        board.addStartLocation(0, 0, 0);
        board.addStartLocation(0, CORNER, CORNER);
        board.addStartLocation(1, 0, CORNER);
        board.addStartLocation(1, CORNER, 0);

        try(Scanner sc = new Scanner(System.in)) {
            while (!board.isGameOver()) {
                System.out.println("---------------------------------------");
                board.startRound();
                System.out.println("Round: " + board.getRound() + " ,Current " + board.getCurrentPlayer());
                board.printBoard();
                boolean moveSuccess = false;
                while (!moveSuccess) {
                    System.out.println("select a place to move.");

                    List<Object> para = Parser.parse(sc.nextLine());
                    int worker = (Integer) para.get(0);
                    Direction dir = (Direction) para.get(1);

                    moveSuccess = board.moveWorker(worker, dir);
                }
                if (board.isGameOver()) {
                    System.out.println("Game Over. Winner is: " + board.getCurrentPlayer());
                    return;
                }
                board.printBoard();
                boolean buildSuccess = false;
                while (!buildSuccess) {
                    System.out.println("select a place to build.");

                    List<Object> para = Parser.parse(sc.nextLine());
                    int worker = (Integer) para.get(0);
                    Direction dir = (Direction) para.get(1);

                    buildSuccess = board.build(worker, dir);
                }
            } // while
        } // try

    } // main
}

