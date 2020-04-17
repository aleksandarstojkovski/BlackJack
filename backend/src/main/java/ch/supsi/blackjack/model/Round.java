package ch.supsi.blackjack.model;

import java.util.List;

public class Round {

    private final Dealer dealer;
    private final List<Player> playerList;

    /*ToDo: player deve diventare una arraylist di player per gestire il multiplayer*/
    public Round(Dealer dealer, List<Player> playerList){
        this.dealer = dealer;
        this.playerList = playerList;
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public List<Player> getPlayerList() {
        return this.playerList;
    }
}
