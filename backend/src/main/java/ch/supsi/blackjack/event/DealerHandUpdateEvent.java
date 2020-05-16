package ch.supsi.blackjack.event;

import ch.supsi.blackjack.model.Hand;
import ch.supsi.blackjack.model.state.round.RoundState;

public class DealerHandUpdateEvent extends AbstractEvent {
    private final int handValue;
    private final int lastCardValue;
    private final int handSize;
    private final RoundState state;

    public DealerHandUpdateEvent(Object source, Hand hand, RoundState state) {
        super(source);
        this.handValue = hand.value();
        this.handSize = hand.size();
        this.lastCardValue = hand.getLastCardValue();
        this.state = state;
    }

    public int getValue() {
        return handValue;
    }
    public int getHandSize(){
        return handSize;
    }
    public int getLastCardValue(){
        return lastCardValue;
    }
    public RoundState getState(){
        return state;
    }
}
