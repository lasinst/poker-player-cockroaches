package org.leanpoker.player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Player {

    static final String VERSION = "All-in Folder Java Player";
    static Logger log = Logger.getLogger(PlayerServlet.class.getName());

    public static int betRequest(JsonElement request) {
        System.out.println("---------BET ----------------");
        int ourBet;

        JsonObject realRequest = request.getAsJsonObject();
        prettyPrint(realRequest);

        Gson parser = new Gson();
        GameState gameState = parser.fromJson(request, GameState.class);
        GamePlayer player = gameState.getCurrentPlayer();

        int min = gameState.current_buy_in - player.bet;
        int stack = player.stack;

        if (fallIfBadCards(player)) {
            return 0;
        }
        
        ourBet = min;

        List<Card> allCards = allCards(gameState.community_cards, player.hole_cards);

        if (Rule.getHands(allCards).getRank() >= Hands.DRILL.getRank()) {
            ourBet = player.stack;
        }

        if (gameState.community_cards.size() == 5 && Rule.getHands(allCards) == Hands.NOTHING) {
            return 0;
        }

        log.info("stack: " + stack);
        log.info("ourBet: " + ourBet);

        return ourBet;
    }

    public static void showdown(JsonElement game) {
        System.out.println("---------GAME END - SHOWDOWN ----------------");
        JsonObject realRequest = game.getAsJsonObject();
        prettyPrint(realRequest);
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
        return player.hole_cards.get(0).rank.getValue() < 10 && player.hole_cards.get(1).rank.getValue() < 10;
    }

    private static List<Card> allCards (List<Card> communityCards, List<Card> holeCards) {
        List<Card> cards = new ArrayList<Card>(communityCards);
        cards.addAll(holeCards);
        return cards;
    }

    private static void prettyPrint(JsonObject realRequest) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(realRequest);
        System.out.println(json);
    }
}