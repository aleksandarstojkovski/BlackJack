package ch.supsi.blackjack.event;

import ch.supsi.blackjack.model.Card;

public class NewCardEvent extends AbstractEvent {
    private int playerId;
    private Card card;

    public NewCardEvent(Object source, Card card, int playerId) {
        super(source);
        this.card = card;
        this.playerId=playerId;
    }

    public Card getCard() {
        return card;
    }
    public int getPlayerId(){
        return playerId;
    }

}
