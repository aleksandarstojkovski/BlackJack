package ch.supsi.blackjack.model;

public class Game {
    private Dealer dealer;
    private Player player;
    /*ToDo: player deve diventare una arraylist di player per gestire il multiplayer*/
    public Game(){
        this.dealer = new Dealer();
        this.player = new Player();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Player getPlayer() {
        return player;
    }
}
