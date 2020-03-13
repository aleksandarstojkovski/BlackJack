package ch.supsi.blackjack.model;

public enum Value {
    ONE("Asso",1),
    TWO("Due", 2),
    THREE("Tre",3),
    FOUR("Quattro",4),
    FIVE("Cinque",5),
    SIX("Sei",6),
    SEVEN("Sette",7),
    EIGHT("Otto",8),
    NINE("Nove",9),
    TEN("Dieci",10),
    JACK("Fante",10),
    QUEEN("Regina",10),
    KING("Re",10);

    public final String label;
    public final int cardValue;

    Value(String label,int cardValue) {
        this.label = label;
        this.cardValue = cardValue;
    }

}
