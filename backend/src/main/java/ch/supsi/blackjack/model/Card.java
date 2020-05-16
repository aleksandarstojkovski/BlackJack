package ch.supsi.blackjack.model;

import java.io.Serializable;
import java.util.Objects;

public class Card implements Serializable {

    public enum BackColor {
        RED,
        BLUE
    }

    private final Seed seed;
    private final Value value;
    private final BackColor back;

    public Card(Builder builder) {
        this.seed = builder.seed;
        this.value = builder.value;
        this.back = builder.back;
    }

    public Seed getSeed() {
        return seed;
    }

    public Value getValue() {
        return value;
    }

    public BackColor getBack(){
        return back;
    }

    @Override
    public String toString() {
        return "Card{" +
                "seed=" + seed.getLabel() +
                ", value=" + value.getLabel() +
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

    public static class Builder {
        private Value value;    // mandatory
        private Seed seed = Seed.D;
        private BackColor back = BackColor.BLUE;

        public Builder(Value value) {
            this.value = value;
        }

        public Builder setSeed(Seed seed) {
            this.seed = seed;
            return this;
        }

        public Builder setValue(Value value) {
            this.value = value;
            return this;
        }

        public Builder setBack(BackColor back) {
            this.back = back;
            return this;
        }

        public Card build() {
            return new Card(this);
        }

    }

}
