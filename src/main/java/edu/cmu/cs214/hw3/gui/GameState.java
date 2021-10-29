package edu.cmu.cs214.hw3.gui;

import edu.cmu.cs214.hw3.core.Board;
import edu.cmu.cs214.hw3.core.Game;
import edu.cmu.cs214.hw3.player.Player;
import edu.cmu.cs214.hw3.util.Location;

public class GameState {

    private final Cell[] cells;
    private Player currPlayer;
    private String nextAvailableAction;
    private Player winner;

    private GameState(Cell[] cells) {
        this.cells = cells;
    }


    public static GameState forGame(Game game) {
        Cell[] cells = getCells(game);
        return new GameState(cells);
    }


    private static Cell[] getCells(Game game) {
        Cell cells[] = new Cell[9];
        Board board = game.getBoard();
        for (int x = 0; x <= 2; x++) {
            for (int y = 0; y <= 2; y++) {
                String text = "";
                String link = "";
                String clazz = "";
                String location = "";
                Player player = board.getWorkerOwner(Location.get(y, x));
                if (player == null) {
                    clazz = "unoccupied";
                    link = "/play?x=" + x + "&y=" + y;
                } else {
                    text = player.symbol();
                }

                cells[3 * y + x] = new Cell(text, clazz, link);
            }
        }
        return cells;
    }

    public void afterSelectingWorker() {

    }

}
