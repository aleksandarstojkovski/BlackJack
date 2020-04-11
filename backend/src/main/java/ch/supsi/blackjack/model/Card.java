package ch.supsi.blackjack.model;

import java.util.Objects;

public class Card {

    private final Seed seed;
    private final Value value;

    public Card(Seed seed, Value value){
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return seed == card.seed &&
                value == card.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seed, value);
    }
}
