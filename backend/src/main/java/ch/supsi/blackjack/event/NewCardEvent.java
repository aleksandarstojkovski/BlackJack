package ch.supsi.blackjack.event;

import ch.supsi.blackjack.model.Card;
import ch.supsi.blackjack.model.Player;

public class NewCardEvent extends AbstractEvent {
    private Player player;
    private Card card;

    public NewCardEvent(Object source, Card card, Player player) {
        super(source);
        this.card = card;
        this.player=player;
    }

    public Card getCard() {
        return card;
    }

    public Player getPlayer(){
        return player;
    }

}
