package ch.supsi.blackjack.event;

import ch.supsi.blackjack.model.Card;
import ch.supsi.blackjack.model.Hand;

public class PlayerHandUpdateEvent extends AbstractEvent {
    private final Card lastCard;
    private final int handValue;

    public PlayerHandUpdateEvent(Object source, Hand hand) {
        super(source);
        this.handValue = hand.value();
        this.lastCard = hand.getLastCard();
    }

    public int getValue() {
        return handValue;
    }

    public Card getLastCard() {
        return lastCard;
    }
}
