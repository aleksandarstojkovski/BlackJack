package ch.supsi.blackjack.event;

import ch.supsi.blackjack.model.RoundStatus;

public class GameOverEvent extends AbstractEvent {
    public GameOverEvent(Object source) {
        super(source);
    }
}
