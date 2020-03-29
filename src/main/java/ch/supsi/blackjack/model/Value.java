package ch.supsi.blackjack.model;

public enum Value {
    ACE("ace", 1),
    TWO("2",  2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("jack", 10),
    QUEEN("queen", 10),
    KING("king", 10);

    public final String label;
    public final int cardValue;

    Value( String label, int cardValue) {
        this.label = label;
        this.cardValue = cardValue;
    }

}
