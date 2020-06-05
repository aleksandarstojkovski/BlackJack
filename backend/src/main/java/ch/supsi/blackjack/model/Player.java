package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.exception.InsufficientCoinsException;

public class Player {

    private int coins;
    private final String nickname;
    public static final int DEFAULT_INITIAL_COINS=1000;

    Player(String nickname, int initialCoins) {
        this.nickname = nickname;
        this.coins = initialCoins;
    }

    Player(String nickname) {
        this.nickname = nickname;
        this.coins = DEFAULT_INITIAL_COINS;
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

