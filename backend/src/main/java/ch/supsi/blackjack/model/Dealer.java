package ch.supsi.blackjack.model;

public class Dealer extends VirtualPlayer {

    public Dealer(String nickname, int initialCoins) {
        super(nickname, initialCoins);
        this.ai = new DealerAI(this);
    }

}
