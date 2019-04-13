package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

public class Player {

    static final String VERSION = "All-in Java Player";

    public static int betRequest(JsonElement request) {
        JsonObject realRequest = request.getAsJsonObject();
        int activePlayer = realRequest.get("in_action").getAsInt();
        JsonObject player = realRequest.get("players").getAsJsonArray().get(activePlayer).getAsJsonObject();

        int stack = player.get("stack").getAsInt();
        int ourBet = player.get("bet").getAsInt();

        return stack;
    }

    public static void showdown(JsonElement game) {
    }
}