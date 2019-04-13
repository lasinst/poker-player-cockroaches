package org.leanpoker.player;

public enum Hands {
    NOTHING(0),
    PAIR(1),
    TWO_PAIR(2),
    DRILL(3),
    STRAIGHT(4),
    FLUSH(5),
    POKER(6),
    STRAIGHT_FLUSH(7);

    private int rank;

    Hands(int i) {
        rank = i;
    }

    public int getRank() {
        return rank;
    }
}
