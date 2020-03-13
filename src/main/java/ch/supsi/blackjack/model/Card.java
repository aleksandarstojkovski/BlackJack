package ch.supsi.blackjack.model;

public class Card {
    private final Seed seed;
    private final Value value;
    Card(Seed seed, Value value){
        this.seed = seed;
        this.value = value;
    }
    public Seed getSeed() {
        return seed;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Card{" +
                "seed=" + seed.label +
                ", value=" + value.label +
                '}';
    }
}
