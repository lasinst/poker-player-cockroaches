package org.leanpoker.player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.logging.Logger;

import java.util.Map;
import java.util.Random;

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

        if(gameState.minimum_raise == player.bet)
        {
            ourBet = 0;
        }
        else
        {
            ourBet = min;
        }

        log.info("stack: " + stack);
        log.info("ourBet: " + ourBet);

        return ourBet;
    }

    public static void showdown(JsonElement game) {
        System.out.println("---------GAME END-----------------");
        JsonObject realRequest = game.getAsJsonObject();
        prettyPrint(realRequest);

    }


    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            return max;
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private static void prettyPrint(JsonObject realRequest){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(realRequest);
        System.out.println(json);
    }
}