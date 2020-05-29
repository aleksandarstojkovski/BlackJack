package ch.supsi.blackjack.event;

import ch.supsi.blackjack.model.Player;

public class DealerStartEvent extends AbstractEvent {

    private final Player player;

    public DealerStartEvent(Object source, Player player) {
        super(source);
        this.player = player;
    }

    public Player getPlayer(){
        return player;
    }

}
