package edu.cmu.cs214.hw3.gui;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import edu.cmu.cs214.hw3.core.Game;
import fi.iki.elonen.NanoHTTPD;

import java.io.IOException;
import java.util.Map;

public class App extends NanoHTTPD {

    private Game game;
    private Template template;

    public App() throws IOException {
        super(8080);

        this.game = new Game();
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
            if (uri.equals("/newgame")) {
                this.game = new Game();
            } else if (uri.equals("/choose")) {
                selectedLocation = (Integer.parseInt(params.get("y")), Integer.parseInt(params.get("x")))
                boolean success = this.game.setActiveWorker(Integer.parseInt(params.get("y")), Integer.parseInt(params.get("x")));
                if (success) {
                    nextAction = game.getNextAction(); // can be optional move or build. if optional, provide skip. (action, must?)
                    // modify all locations except for selected.
//                    gameplay = GameState.offerAfterSelection(game, nextAction);
                    gameplay.proceed();
                    HTML..... return...
                }
            } else if (uri.equals("/move")) {
                destination = (Integer.parseInt(params.get("y")), Integer.parseInt(params.get("x")))
                boolean success = this.game.move(destination);
                if (success) {
                    gameplay = Gamestate.setSelectionState(game); // make all cells selectable -> href: /choose
                    HTML..... return...
                }
            } else if (uri.equals("/build")) {
                destination = (Integer.parseInt(params.get("y")), Integer.parseInt(params.get("x")))
                boolean success = this.game.build(destination);
                if (success) {
                    gameplay = Gamestate.setSelectionState(game); // make all cells selectable -> href: /choose
                    HTML.....return...
                }
            } else if (uri.equals("/skip")) {
                boolean success = this.game.skip();
                if (success) {
                    gameplay = Gamestate.setSelectionState(game); // make all cells selectable -> href: /choose
                    HTML..... return...
                }
            }
            doNothing... return HTML;




            // Extract the view-specific data from the game and apply it to the template.
            GameState gameplay = GameState.forGame(this.game);
            String HTML = this.template.apply(gameplay);
            return newFixedLengthResponse(HTML);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }

}
