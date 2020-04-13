package ch.supsi.blackjack.event;

import ch.supsi.blackjack.model.Player;

import java.util.List;

public class NewGameEvent extends AbstractEvent {

    private List<Player> playerList;

    public NewGameEvent(Object source, List<Player> playerList) {
        super(source);
        this.playerList=playerList;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

}