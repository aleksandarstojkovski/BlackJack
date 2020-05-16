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

    public DecksContainer(int numberOfDecks) throws InvalidDecksContainerSizeException {
        if (numberOfDecks > MIN_NUMBER_OF_DECKS  && numberOfDecks < MAX_NUMBER_OF_DECKS){
            this.numberOfDecks=numberOfDecks;
            for (int i=0; i<numberOfDecks; i++){
                Deck deck = new Deck();
                availableCards.addAll(Arrays.asList(deck.getCards()));
            }
        } else {
            throw new InvalidDecksContainerSizeException("Size of the Deck Container must be "+MAX_NUMBER_OF_DECKS+">"+"size"+">"+MIN_NUMBER_OF_DECKS+".");
        }
    }

    public void shuffle(){
        Collections.shuffle(availableCards);
    }

    // TODO verify empty availableCards (define strategy)
    public Card getCard(){
        Card c = availableCards.remove(0);
        usedCards.add(c);
        return c;
    }

    public int getAvailableCardsCount(){
        return availableCards.size();
    }

    public void pushBackUsedCards(){
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

}
