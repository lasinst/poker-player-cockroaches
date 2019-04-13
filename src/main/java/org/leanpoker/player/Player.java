package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;
import java.util.Random;

public class Player {

    static final String VERSION = "All-in Java Player";

    public static int betRequest(JsonElement request) {
        JsonObject realRequest = request.getAsJsonObject();
        int activePlayer = realRequest.get("in_action").getAsInt();
        JsonObject player = realRequest.get("players").getAsJsonArray().get(activePlayer).getAsJsonObject();

        int min = realRequest.get("current_buy_in").getAsInt() + realRequest.get("minimum_raise").getAsInt();
        int stack = player.get("stack").getAsInt();

        return getRandomNumberInRange(min, stack);
    }

    public static void showdown(JsonElement game) {
    }


    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}