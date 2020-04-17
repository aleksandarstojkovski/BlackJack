package ch.supsi.blackjack.event;

import ch.supsi.blackjack.model.Player;

import java.util.List;

public class GameStartedEvent extends AbstractEvent {

    private final List<Player> playerList;

    public GameStartedEvent(Object source, List<Player> playerList) {
        super(source);
        this.playerList=playerList;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

}