package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.exception.InsufficientCoinsException;

public class Player {
    private final int playerID;
    private static final int INITIAL_COINS=1000;
    private String nickname;
    protected Hand hand;
    private int coins;
    protected AI ai;

    Player(String nickname, int playerID) {
        this.playerID = playerID;
        this.nickname = nickname;
        this.hand = new Hand();
        this.coins = INITIAL_COINS;
        this.ai = new PlayerAI(this);
    }

    public AI getAi() {
        return ai;
    }

    private void addCardToHand (Card newCard){
        this.hand.addCard(newCard);
    }

    public Hand getHand() {
        return this.hand;
    }

    public int getPlayerID() {
        return playerID;
    }

    public String getNickname() {
        return nickname;
    }

    public int getCoins() {
        return coins;
    }

    public void bet(int amount) throws InsufficientCoinsException{
        if (coins>=amount){
            coins-=amount;
            hand.addBet(amount);
        } else{
            throw new InsufficientCoinsException(nickname + " has " + coins + " coins and tries to bet " + amount + " coins.");
        }
    }

    public int handValue() {
        return getHand().value();
    }
}

