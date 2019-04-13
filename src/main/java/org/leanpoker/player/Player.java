package org.leanpoker.player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.logging.Logger;

public class Player {

    static final String VERSION = "All-in Java Player";
    static Logger log = Logger.getLogger(PlayerServlet.class.getName());

    public static int betRequest(JsonElement request) {

        int ourBet;

        JsonObject realRequest = request.getAsJsonObject();
        prettyPrint(realRequest);

        Gson parser = new Gson();
        GameState gameState = parser.fromJson(request, GameState.class);
        GamePlayer player = gameState.getCurrentPlayer();

        int min = gameState.current_buy_in;
        int stack = player.stack;

        if (fallIfBadCards(player)) {
            return 0;
        }

        if (gameState.minimum_raise == player.bet) {
            ourBet = 0;
        } else {
            ourBet = min;
        }
        log.info("stack: " + stack);
        log.info("ourBet: " + ourBet);

        return ourBet;
    }

    private static boolean fallIfBadCards(GamePlayer player) {
        return smallCards(player) && !sameSuits(player) && !pairs(player);
    }

    private static boolean pairs(GamePlayer player) {
        return player.hole_cards.get(0).rank == player.hole_cards.get(1).rank;
    }

    private static boolean sameSuits(GamePlayer player) {
        return player.hole_cards.get(0).suit == player.hole_cards.get(1).suit;
    }

    private static boolean smallCards(GamePlayer player) {
        return player.hole_cards.get(0).rank.getValue() < 6 && player.hole_cards.get(1).rank.getValue() < 6;
    }

    public static void showdown(JsonElement game) {
    }

    private static void prettyPrint(JsonObject realRequest) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(realRequest);
        System.out.println(json);
    }
}