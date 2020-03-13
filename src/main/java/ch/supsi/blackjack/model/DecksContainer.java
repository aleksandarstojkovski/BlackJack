package ch.supsi.blackjack.model;

import java.util.*;

public class DecksContainer {

    private static final int MIN_NUMBER_OF_DECKS=1;
    private static final int MAX_NUMBER_OF_DECKS=5;
    private int numberOfDecks;
    private List<Card> cards = new ArrayList<>();

    public DecksContainer(int numberOfDecks) throws InvalidDeckContainerSizeException {
        if (numberOfDecks > MIN_NUMBER_OF_DECKS  && numberOfDecks < MAX_NUMBER_OF_DECKS){
            this.numberOfDecks=numberOfDecks;
            for (int i=0; i<numberOfDecks; i++){
                Deck deck = new Deck();
                cards.addAll(Arrays.asList(deck.getCards()));
            }
        } else {
            throw new InvalidDeckContainerSizeException("Size of the Deck Container must be "+MAX_NUMBER_OF_DECKS+">"+"size"+">"+MIN_NUMBER_OF_DECKS+".");
        }
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    @Override
    public boolean equals(Object o) {
        DecksContainer that = (DecksContainer) o;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (that.cards.size() != this.cards.size()) return false;
        for (int i=0;i<this.cards.size();i++){
            if (this.cards.get(i)!=that.cards.get(i)){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfDecks, cards);
    }
}
