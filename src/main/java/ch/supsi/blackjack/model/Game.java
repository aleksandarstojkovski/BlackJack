package ch.supsi.blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Dealer dealer;
    private List<Player> playerList;
    /*ToDo: player deve diventare una arraylist di player per gestire il multiplayer*/
    public Game(Dealer dealer, ArrayList<Player> playerList){
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
