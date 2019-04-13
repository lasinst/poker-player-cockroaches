package org.leanpoker.player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rule {

    static Hands getHands(List<Card> cards) {
        if (hasPoker(cards)) {
            return Hands.POKER;
        }
        if (hasFullHouse(cards)) {
            return Hands.FULL_HOUSE;
        }

        if (hasDrill(cards)) {
            return Hands.DRILL;
        }

        if (hasTwoPair(cards)) {
            return Hands.TWO_PAIR;
        }

        if (hasPair(cards)) {
            return Hands.PAIR;
        }

        return Hands.NOTHING;
    }

    private static boolean hasFullHouse(List<Card> cards) {
        return hasPair(cards) && hasDrill(cards);
    }

    private static boolean hasDrill(List<Card> cards) {
        return areRankSame(cards, 3);
    }

    private static boolean hasPair(List<Card> cards) {
        return areRankSame(cards, 2);
    }

    private static boolean hasPoker(List<Card> cards) {
        return areRankSame(cards, 4);
    }

    private static boolean hasTwoPair(List<Card> cards) {
        return areTwoSame(cards);
    }

    private static boolean areRankSame(List<Card> cards, int number) {
        if (cards.size() >= number) {
            Map<Integer, Integer> map = new HashMap<>();
            for (Card card : cards) {
                Integer integer = map.get(card.rank.getValue());
                if (integer == null) {
                    integer = 1;
                } else {
                    integer++;
                }
                map.put(card.rank.getValue(), integer);
            }
            for (int i : map.values()) {
                if (i == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean areTwoSame(List<Card> cards) {
        int pairs = 0;
        if (cards.size() >= 4) {
            Map<Integer, Integer> map = new HashMap<>();
            for (Card card : cards) {
                Integer integer = map.get(card.rank.getValue());
                if (integer == null) {
                    integer = 1;
                } else {
                    integer++;
                }
                map.put(card.rank.getValue(), integer);
            }
            for (int i : map.values()) {
                if (i == 2) {
                    pairs++;
                }
            }
        }
        return pairs == 2;
    }
}
