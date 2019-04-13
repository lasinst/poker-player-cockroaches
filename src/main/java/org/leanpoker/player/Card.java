package org.leanpoker.player;

import com.google.gson.annotations.SerializedName;

public class Card {
    public Rank rank;
    public Suits suit;
}

enum Rank {
    @SerializedName("2")
    TWO(2),
    @SerializedName("3")
    THREE(3),
    @SerializedName("4")
    FOUR(4),
    @SerializedName("5")
    FIVE(5),
    @SerializedName("6")
    SIX(6),
    @SerializedName("7")
    SEVEN(7),
    @SerializedName("8")
    EIGHT(8),
    @SerializedName("9")
    NINE(9),
    @SerializedName("10")
    TEN(10),
    @SerializedName("J")
    J(11),
    @SerializedName("Q")
    Q(12),
    @SerializedName("K")
    K(13),
    @SerializedName("A")
    A(14);


    private int value;

    Rank(int i) {
        value = i;
    }

    public int getValue() {
        return value;
    }

}

enum Suits {
    @SerializedName("clubs")
    CLUB,
    @SerializedName("spades")
    SPADE,
    @SerializedName("hearts")
    HEART,
    @SerializedName("diamonds")
    DIAMOND
}
