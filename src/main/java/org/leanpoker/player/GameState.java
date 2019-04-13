package org.leanpoker.player;

import java.util.List;
import java.util.ArrayList;


public class GameState {

    public String tournament_id;
    public String game_id;
    public int round;
    public int bet_index;
    public int small_blind;
    public int current_buy_in;
    public int pot;
    public int minimum_raise;
    public int dealer;
    public int orbits;
    public int in_action;
    public List<GamePlayer> players;
    public List<Card> community_cards;

    public GamePlayer getCurrentPlayer() {
        return players.get(in_action);
    }

    public boolean anyAllIn() {
        List<GamePlayer> otherPlayers = new ArrayList<GamePlayer>();
        otherPlayers.addAll(players);
        otherPlayers.remove(in_action);

        for (GamePlayer p : otherPlayers) {
            if (p.stack == 0 && p.bet > 0) {
                return true;
            }
        }
        return false;
    }
}
