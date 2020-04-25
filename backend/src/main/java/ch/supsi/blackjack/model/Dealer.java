package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.exception.InvalidDecksContainerSizeException;

public class Dealer extends VirtualPlayer {
    private DecksContainer decksContainer;

    public Dealer() {
        super("Dealer",99);
        this.ai = new DealerAI(this);
        try {
            decksContainer = new DecksContainer(3);
            // ToDo: rendere modificabile il numero di mazzi
        } catch (InvalidDecksContainerSizeException e) {
            e.printStackTrace();
        }
        decksContainer.shuffle();
    }

    public Card giveCard(){
        return decksContainer.getCard();
    }
}
