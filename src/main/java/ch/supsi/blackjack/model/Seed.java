package ch.supsi.blackjack.model;

public enum Seed {
    H("Cuori"),
    D("Quadri"),
    C("Fiori"),
    S("Picche");

    public final String label;

    Seed(String label) {
        this.label = label;
    }
}
