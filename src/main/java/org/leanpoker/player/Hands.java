package org.leanpoker.player;

public enum Hands {
    NOTHING(0),
    PAIR(1),
    TWO_PAIR(2),
    DRILL(3),
    STRAIGHT(4),
    FLUSH(5),
    FULL_HOUSE(6),
    POKER(7),
    STRAIGHT_FLUSH(8);

    private int rank;

    Hands(int i) {
        rank = i;
    }

    public int getRank() {
        return rank;
    }
}
