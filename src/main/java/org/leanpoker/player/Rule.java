package org.leanpoker.player;

import java.util.*;
import java.util.stream.Collectors;

public class Rule {

    static Hands getHands(List<Card> cards) {

        if (hasStaightFlush(cards)) {
            return Hands.STRAIGHT_FLUSH;
        }

        if (hasPoker(cards)) {
            return Hands.POKER;
        }

        if (hasFullHouse(cards)) {
            return Hands.FULL_HOUSE;
        }

        if (hasStraight(cards)) {
            return Hands.STRAIGHT;
        }

        if (hasFlush(cards)) {
            return Hands.FLUSH;
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

    private static boolean hasStaightFlush(List<Card> cards) {
        return hasStraight(cards) && hasFlush(cards);
    }

    private static boolean hasStraight(List<Card> cards) {

        int consecutive = 0;
        if (cards.size() >= 5) {
            Set<Integer> integers = new HashSet<>(7);
            for (Card card : cards) {
                integers.add(card.rank.getValue());
            }
            List<Integer> sortedList = integers.stream().sorted().collect(Collectors.toList());

            for (int i = 0; i < sortedList.size() - 1; i++) {
                if (sortedList.get(i) + 1 == sortedList.get(i + 1)) {
                    consecutive++;
                } else {
                    consecutive = 0;
                }
            }
        }
        return consecutive == 4;
    }

    private static boolean hasFlush(List<Card> cards) {
        if (cards.size() >= 5) {
            Map<Suits, Integer> map = new HashMap<>();
            for (Card card : cards) {
                Integer integer = map.get(card.suit);
                if (integer == null) {
                    integer = 1;
                } else {
                    integer++;
                }
                map.put(card.suit, integer);
            }
            for (int i : map.values()) {
                if (i == 5) {
                    return true;
                }
            }
        }
        return false;
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
