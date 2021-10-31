package edu.cmu.cs214.hw3.gui;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.core.Game;
import edu.cmu.cs214.hw3.gameLogic.*;
import edu.cmu.cs214.hw3.listeners.GameAction;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.player.Worker;
import edu.cmu.cs214.hw3.util.Location;

import java.util.List;

public class GameState {

    private static final int COLS = 5;
    private static final int ROWS = 5;
    private String instruction;
    private Game game;
    private Cell[] cells;
    private Player currPlayer;
    private List<Player> players;
    private Worker selectedWorker;
//    private Player winner;
//    private boolean workerInitiated;
    private int pickingGodIndex;
    private int pickingLocationIndex;


    public GameState(Game game) {
        this.game = game;
        pickingLocationIndex = 0;
        pickingGodIndex = 0;
//        workerInitiated = false;

        // cells with choose link.
        cells = startCells(game);
//        winner = game.getWinner();
        currPlayer = game.getCurrentPlayer();
        players = game.getPlayers();
        instruction = "Select god card for player " + players.get(getPlayerSelecting()).symbol();
    }


    public Cell[] resetCells(Game game) {
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


    public Cell[] startCells(Game game) {
        Cell[] cells = new Cell[25];
        Board board = game.getBoard();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                String text;
                String clazz;
                String action = "pickStartLocation";
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
        if (game.getWinner() != null) {
            instruction = "Game over. Please start a new game.";
            return false;
        }

        Worker selected = game.getBoard().getWorkerOnField(Location.get(row, col));
        if (selected == null) {
            instruction = "Invalid location selected";
            return false;
        }

        selectedWorker = selected;
        GameAction nextAction = game.nextGameAction();
        instruction = selected + " is selected! Next Action is " + nextAction.getActionName();
        // update the cells
        for (int i = 0; i < ROWS * COLS; i++) {
            cells[i].setAction(nextAction.getActionName());
        }
        return true;
    }


    public boolean move(int row, int col) {
        boolean success = game.moveWorker(currPlayer, selectedWorker.getLocation(), Location.get(row, col));
        if (success) {
            updatePlayer();
            instruction = "move success. Choose the worker for next action.";
        } else {
            instruction = "move failed. Choose the worker for next action.";
        }
        updateGameChangeToCells();
        return success;
    }

    private void updatePlayer() {
        currPlayer = game.getCurrentPlayer();
    }


    public boolean build(int row, int col) {
        // update the cells
        boolean success =  game.build(currPlayer, selectedWorker.getLocation(), Location.get(row, col));
        if (success) {
            updatePlayer();
            instruction = "build success. Choose the worker for next action.";
        } else {
            instruction = "build failed. Choose the worker for next action.";
        }
        updateGameChangeToCells();
        return success;
    }

    public boolean skip() {
        // update the cells

        try {
            game.skipAction(currPlayer);
            updatePlayer();
            updateGameChangeToCells();
            instruction = "Skip success. Choose the worker for next action.";
            return true;
        } catch (Exception e) {
            instruction = e.getMessage();
            updateGameChangeToCells();
            return false;
        }
    }

    private void updateGameChangeToCells() {
        cells = resetCells(game);
    }


    public void assignGameLogic(int player, String godCard) {
        GameLogic gameLogic = new BasicGameLogic();
        switch (godCard) {
            case "NoGod": break;
            case "Apollo":
                gameLogic = new ApolloGameLogic(gameLogic);
                break;
            case "Artemis":
                gameLogic = new ArtemisGameLogic(gameLogic);
                break;
            case "Athena":
                gameLogic = new AthenaGameLogic(gameLogic);
                break;
            case "Atlas":
                gameLogic = new AtlasGameLogic(gameLogic);
                break;
            case "Demeter":
                gameLogic = new DemeterGameLogic(gameLogic);
                break;
            case "Hephaestus":
                gameLogic = new HephaestusGameLogic(gameLogic);
                break;
            case "Hermes":
                gameLogic = new HermesGameLogic(gameLogic);
                break;
            case "Minotaur":
                gameLogic = new MinotaurGameLogic(gameLogic);
                break;
            case "Pan":
                gameLogic = new PanGameLogic(gameLogic);
                break;
        }
        game.assignGameLogic(players.get(player), gameLogic);
        pickingGodIndex++;
        if (getGodSelected()) {
            instruction = "Gods all set. Pick start location for workers.";
        } else {
            instruction = "Select god card for player " + players.get(getPlayerSelecting()).symbol();
        }
    }



    public Cell[] getCells() {
        return cells;
    }

    public String getInstruction() {
        return instruction;
    }

    public String getCurrPlayer() {
        return currPlayer.symbol();
    }

    public String getWinner() {
        if (game.getWinner() == null) {
            return null;
        }
        return game.getWinner().symbol();
    }

    public boolean getGodSelected() {
        return pickingGodIndex >= 2;
    }

    public int getPlayerSelecting() {
        return pickingGodIndex;
    }

    public int getPlayerPickingLocation() {
        return pickingLocationIndex / 2;
    }

    public boolean getWorkerInitiated() {
        return pickingLocationIndex >= 4;
    }

    public void pickStartLocation(int playerIndex, int row, int col) {
        if (game.placeWorker(players.get(playerIndex), Location.get(row, col))) {
            pickingLocationIndex++;
            cells = startCells(game);
            instruction = "Pick start location for player " + players.get(getPlayerPickingLocation()).symbol();
        }
        if (pickingLocationIndex >= 4) {
            instruction = "Start locations all set. Now game begins";
            cells = resetCells(game);
        }
    }
}