package ch.supsi.blackjack.event;

import ch.supsi.blackjack.model.RoundStatus;

public class RoundCompletedEvent extends AbstractEvent {
    private final RoundStatus roundStatus;

    public RoundCompletedEvent(Object source, RoundStatus roundStatus) {
        super(source);
        this.roundStatus = roundStatus;
    }

    public RoundStatus getRoundStatus() {
        return roundStatus;
    }
}
