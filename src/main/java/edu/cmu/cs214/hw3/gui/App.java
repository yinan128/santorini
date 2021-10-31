package edu.cmu.cs214.hw3.gui;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import edu.cmu.cs214.hw3.core.Game;
import fi.iki.elonen.NanoHTTPD;

import java.io.IOException;
import java.util.Map;

public class App extends NanoHTTPD {

    public static void main(String[] args) {
        try {
            new App();
        } catch (IOException ioe) {
            System.err.println("Couldn't start server:\n" + ioe);
        }
    }

    private GameState gameState;
    private Template template;


    public App() throws IOException {
        super(8080);

        this.gameState = new GameState(new Game());
        Handlebars handlebars = new Handlebars();
        this.template = handlebars.compile("main");


        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("\nRunning! Point your browsers to http://localhost:8080/ \n");
    }

    @Override
    public Response serve(IHTTPSession session) {
        try {
            String uri = session.getUri();
            Map<String, String> params = session.getParms();
            switch (uri) {
                case "/newgame" -> gameState = new GameState(new Game());
                case "/choose" -> gameState.setActiveWorker(Integer.parseInt(params.get("row")), Integer.parseInt(params.get("col")));
                case "/move" -> gameState.move(Integer.parseInt(params.get("row")), Integer.parseInt(params.get("col")));
                case "/build" -> gameState.build(Integer.parseInt(params.get("row")), Integer.parseInt(params.get("col")));
                case "/skip" -> gameState.skip();
                case "/chooseGod" -> gameState.assignGameLogic(Integer.parseInt(params.get("player")), params.get("god"));
                case "/pickStartLocation" -> gameState.pickStartLocation(
                        Integer.parseInt(params.get("player")),
                        Integer.parseInt(params.get("row")),
                        Integer.parseInt(params.get("col")));
            }
            // game state not changed.
            String HTML = this.template.apply(gameState);
            return newFixedLengthResponse(HTML);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
