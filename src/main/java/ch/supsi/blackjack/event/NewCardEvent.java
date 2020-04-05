package ch.supsi.blackjack.event;

import ch.supsi.blackjack.model.Card;

public class NewCardEvent extends AbstractEvent {

    private Card card;

    public NewCardEvent(Object source, Card card) {
        super(source);
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

}
