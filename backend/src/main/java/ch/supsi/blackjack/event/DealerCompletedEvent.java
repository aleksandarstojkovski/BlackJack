package ch.supsi.blackjack.event;

import ch.supsi.blackjack.model.Player;

public class DealerCompletedEvent extends AbstractEvent {
    public DealerCompletedEvent(Object source) {
        super(source);
    }
}
