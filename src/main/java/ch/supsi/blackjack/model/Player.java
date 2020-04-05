package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.exception.InsufficientCoinsException;

public class Player {

    private static final int INITIAL_COINS=1000;
    private String nickname;
    private Hand hand;
    private int coins;

    Player(String nickname) {
        this.nickname = nickname;
        this.hand = new Hand();
        this.coins = INITIAL_COINS;
    }

    private void addCardToHand (Card newCard){
        this.hand.addCard(newCard);
    }

    public Hand getHand() {
        return this.hand;
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


}

