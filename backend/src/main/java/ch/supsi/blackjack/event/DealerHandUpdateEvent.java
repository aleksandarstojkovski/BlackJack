package ch.supsi.blackjack.event;

import ch.supsi.blackjack.model.Card;
import ch.supsi.blackjack.model.Hand;
import ch.supsi.blackjack.model.state.round.RoundState;

public class DealerHandUpdateEvent extends AbstractEvent {

    private final int handValue;
    private final int handSize;
    private final Card lastCard;
    private final RoundState state;

    public DealerHandUpdateEvent(Object source, Hand hand, RoundState state) {
        super(source);
        this.handValue = hand.value();
        this.handSize = hand.size();
        this.lastCard = hand.getLastCard();
        this.state = state;
    }

    public int getValue() {
        return handValue;
    }

    public int getHandSize(){
        return handSize;
    }

    public RoundState getState(){
        return state;
    }

    public Card getLastCard() {
        return lastCard;
    }

}
