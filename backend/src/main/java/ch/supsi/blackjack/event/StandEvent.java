package ch.supsi.blackjack.event;

import ch.supsi.blackjack.model.Player;

public class StandEvent extends AbstractEvent {

    private final Player player;

    public StandEvent(Object source, Player player) {
        super(source);
        this.player = player;
    }

    public Player getPlayer(){
        return player;
    }

}
