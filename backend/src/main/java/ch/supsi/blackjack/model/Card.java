package ch.supsi.blackjack.model;

import java.io.Serializable;
import java.util.Objects;

public class Card implements Serializable {

    private final Seed seed;
    private final Value value;
    private final String back;

    public Card(Seed seed, Value value, String back){
        this.seed = seed;
        this.value = value;
        this.back = back;
    }

    public Seed getSeed() {
        return seed;
    }

    public Value getValue() {
        return value;
    }

    public String getBack(){
        return back;
    }
    @Override
    public String toString() {
        return "Card{" +
                "seed=" + seed.label +
                ", value=" + value.label +
                ", back=" + back +
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
