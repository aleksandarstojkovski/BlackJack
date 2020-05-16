package ch.supsi.blackjack.model;

public enum Seed {

    H("hearts"),
    D("diamonds"),
    C("clubs"),
    S("spades");

    private final String label;

    Seed(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
