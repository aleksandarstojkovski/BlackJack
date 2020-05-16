package ch.supsi.blackjack.model;

public enum Value {

    ACE("ace", 11),
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

    private final String label;
    private final int defaultValue;

    Value( String label, int defaultValue) {
        this.label = label;
        this.defaultValue = defaultValue;
    }

    public String getLabel() {
        return label;
    }

    public int getDefaultValue() {
        return defaultValue;
    }

}
