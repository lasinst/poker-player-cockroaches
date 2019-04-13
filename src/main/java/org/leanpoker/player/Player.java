package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;

import java.util.Map;

public class Player {

    static final String VERSION = "All-in Java Player";
    static Logger log = Logger.getLogger(PlayerServlet.class.getName());

    public static int betRequest(JsonElement request) {
        JsonObject realRequest = request.getAsJsonObject();
        int activePlayer = realRequest.get("in_action").getAsInt();
        JsonObject player = realRequest.get("players").getAsJsonArray().get(activePlayer).getAsJsonObject();

        int stack = player.get("stack").getAsInt();
        int ourBet = player.get("bet").getAsInt();
        log.info("stack: " + stack);

        return stack;
    }

    public static void showdown(JsonElement game) {
    }
}