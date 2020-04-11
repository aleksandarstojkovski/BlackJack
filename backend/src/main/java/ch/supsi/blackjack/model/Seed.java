package ch.supsi.blackjack.model;

public enum Seed {

    H("hearts"),
    D("diamonds"),
    C("clubs"),
    S("spades");

    public final String label;

    Seed(String label) {
        this.label = label;
    }

}
