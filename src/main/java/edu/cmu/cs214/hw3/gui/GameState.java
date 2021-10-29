package edu.cmu.cs214.hw3.gui;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.core.Game;
import edu.cmu.cs214.hw3.listeners.GameAction;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.util.Location;

import java.util.Arrays;

public class GameState {

    private static final int COLS = 5;
    private static final int ROWS = 5;
    private Game game;
    private final Cell[] cells;
    private Player currPlayer;
    private Worker selectedWorker;
//    private Player winner;
//    private boolean workerInitiated;
//    private boolean godSelected;


    public GameState(Game game) {
        this.game = game;
//        godSelected = false;
//        workerInitiated = false;
        // cells with choose link.
        cells = getCells(game);
        System.out.println(Arrays.toString(cells));
//        winner = game.getWinner();
        currPlayer = game.getCurrentPlayer();
    }


    public Cell[] getCells(Game game) {
        Cell[] cells = new Cell[25];
        Board board = game.getBoard();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                String text;
                String clazz;
                String action = "choose";
                String location = "row=" + row + "&col=" + col;
                Player player = board.getWorkerOwner(Location.get(row, col));
                if (player == null) {
                    clazz = "unoccupied";
                    text = String.valueOf(board.getField(Location.get(row, col)).getHeight());
                } else {
                    clazz = "occupied";
                    text = player.symbol();
                }
                cells[row * COLS + col] = new Cell(text, clazz, action, location);
            }
        }
        return cells;
    }


    public boolean setActiveWorker(int row, int col) {
        Worker selected = game.getBoard().getWorkerOnField(Location.get(row, col));
        if (selected == null) return false;

        selectedWorker = selected;
        GameAction nextAction = game.nextGameAction();
        // update the cells
        for (int i = 0; i < ROWS * COLS; i++) {
            cells[i].setAction(nextAction.getActionName());
        }
        return true;
    }


    public boolean move(int row, int col) {
        // update the cells
        resetCellsToChooseState();
        updatePlayer();
        boolean success = game.moveWorker(currPlayer, selectedWorker.getLocation(), Location.get(row, col));
        updatePlayer();
        return success;
    }

    private void updatePlayer() {
        currPlayer = game.getCurrentPlayer();
    }


    public boolean build(int row, int col) {
        // update the cells
        resetCellsToChooseState();
        boolean success =  game.build(currPlayer, selectedWorker.getLocation(), Location.get(row, col));
        updatePlayer();
        return success;
    }

    public boolean skip() {
        // update the cells
        resetCellsToChooseState();
        try {
            game.skipAction(currPlayer);
            updatePlayer();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void resetCellsToChooseState() {
        for (int i = 0; i < ROWS * COLS; i++) {
            cells[i].setAction("choose");
        }
    }
}
