package ch.supsi.blackjack.model;

import java.util.*;

public class Deck {

    private final Card[] cards;

    Deck(Builder deckBuilder) {
        Set<Card> cardSet = new HashSet<>();

        var cardBuilder = new Card.Builder().setBack(deckBuilder.backColor);
        for(Value value : deckBuilder.values) {
            cardBuilder.setValue(value);
            for(Seed seed : deckBuilder.seeds) {
                Card card = cardBuilder.setSeed(seed).build();
                cardSet.add(card);
            }
        }
        cards = new Card[cardSet.size()];
        cardSet.toArray(cards);
    }

    public Card[] getCards() {
        return cards;
    }

    /**
     * Deck Builder
     */
    public static class Builder {
        private EnumSet<Value> values = EnumSet.allOf(Value.class);
        private EnumSet<Seed> seeds = EnumSet.allOf(Seed.class);
        private Card.BackColor backColor = Card.BackColor.BLUE;

        public Builder() {
        }

        public Builder setSeeds(EnumSet<Seed> seeds) {
            this.seeds = seeds;
            return this;
        }

        public Builder setValues(EnumSet<Value> values) {
            this.values = values;
            return this;
        }

        public Builder setBackColor(Card.BackColor backColor) {
            this.backColor = backColor;
            return this;
        }

        public Deck build() {
            return new Deck(this);
        }
    }
}
