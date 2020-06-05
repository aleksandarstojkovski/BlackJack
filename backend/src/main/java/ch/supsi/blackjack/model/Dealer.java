package ch.supsi.blackjack.model;

public class Dealer extends VirtualPlayer {

    public static final int DEFAULT_INITIAL_COINS=1000000;

    public Dealer(String nickname, int initialCoins) {
        super(nickname, initialCoins);
        this.ai = new DealerAI(this);
    }

    public Dealer(String nickname) {
        super(nickname, DEFAULT_INITIAL_COINS);
        this.ai = new DealerAI(this);
    }

}
