package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.exception.InsufficientCoinsException;

public class Player {
    
    private final String nickname;
    private int coins;

    Player(String nickname, int initialCoins) {
        this.nickname = nickname;
        this.coins = initialCoins;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean hasMoney() {
        return coins>0;
    }

    public void giveCoins(int amount) {
        coins+=amount;
    }

    public int takeCoins(int amount) throws InsufficientCoinsException{
        if (amount<=coins) {
            coins -= amount;
        } else {
            throw new InsufficientCoinsException(nickname + " has " + coins + " coins and tries to bet " + amount + " coins.");
        }
        return amount;
    }

    public int getCoinsAmount(){
        return coins;
    }

}

