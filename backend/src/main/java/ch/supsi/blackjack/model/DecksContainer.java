package ch.supsi.blackjack.model;


import ch.supsi.blackjack.model.exception.InvalidDecksContainerSizeException;

import java.io.Serializable;
import java.util.*;

public class DecksContainer implements Serializable {

    public static final int DEFAULT_NUMBER_OF_DECKS=3;
    private static final int MIN_NUMBER_OF_DECKS=1;
    private static final int MAX_NUMBER_OF_DECKS=5;

    private final int numberOfDecks;
    private final List<Card> availableCards = new ArrayList<>();
    private final List<Card> usedCards = new ArrayList<>();

    private DecksContainer(Builder builder) {
        this.numberOfDecks=builder.numberOfDecks;
        for (int i=0; i<numberOfDecks; i++){
            Card.BackColor backColor = (i%2 == 0) ? Card.BackColor.RED : Card.BackColor.BLUE;
            Deck deck = new Deck.Builder()
                    .setValues(builder.values)
                    .setSeeds(builder.seeds)
                    .setBackColor(backColor)
                    .build();
            availableCards.addAll(Arrays.asList(deck.getCards()));
        }
    }

    public void shuffle(){
        Collections.shuffle(availableCards);
    }

    public Card getCard(){
        if(availableCards.size() == 0)
            pushBackUsedCards();

        Card c = availableCards.remove(0);
        usedCards.add(c);
        return c;
    }

    int getAvailableCardsCount(){
        return availableCards.size();
    }

    private void pushBackUsedCards(){
        availableCards.addAll(usedCards);
        usedCards.clear();
        this.shuffle();
    }

    @Override
    public boolean equals(Object o) {
        DecksContainer that = (DecksContainer) o;
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (that.availableCards.size() != this.availableCards.size())
            return false;
        for (int i = 0; i<this.availableCards.size(); i++){
            if (! this.availableCards.get(i).equals(that.availableCards.get(i))){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfDecks, availableCards);
    }

    /**
     * DecksContainer Builder
     */
    public static class Builder {
        EnumSet<Value> values = EnumSet.allOf(Value.class);
        EnumSet<Seed> seeds = EnumSet.allOf(Seed.class);
        Card.BackColor backColor = Card.BackColor.BLUE;
        int numberOfDecks = 3;

        public Builder numberOfDecks(int numberOfDecks) throws InvalidDecksContainerSizeException  {
            if (numberOfDecks >= MIN_NUMBER_OF_DECKS  && numberOfDecks <= MAX_NUMBER_OF_DECKS){
                this.numberOfDecks = numberOfDecks;
                return this;
            } else {
                throw new InvalidDecksContainerSizeException("Size of the Deck Container must be between " + MIN_NUMBER_OF_DECKS + " and " + MAX_NUMBER_OF_DECKS);
            }
        }

        public Builder seeds(EnumSet<Seed> seeds) {
            this.seeds = seeds;
            return this;
        }

        public Builder values(EnumSet<Value> values) {
            this.values = values;
            return this;
        }

        public Builder backColor(Card.BackColor backColor) {
            this.backColor = backColor;
            return this;
        }

        public DecksContainer build() {
            return new DecksContainer(this);
        }
    }

}
