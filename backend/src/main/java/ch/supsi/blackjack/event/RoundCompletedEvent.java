package ch.supsi.blackjack.event;

import ch.supsi.blackjack.model.RoundResult;

public class RoundCompletedEvent extends AbstractEvent {
    private final RoundResult roundResult;

    public RoundCompletedEvent(Object source, RoundResult roundResult) {
        super(source);
        this.roundResult = roundResult;
    }

    public RoundResult getRoundResult() {
        return roundResult;
    }
}
