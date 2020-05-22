package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.exception.InsufficientCoinsException;
import ch.supsi.blackjack.model.Command;

import java.util.HashMap;

public class Player {

    private final HashMap<String, Command> commandMap = new HashMap<>();

    private static final int INITIAL_COINS=1000;
    private final String nickname;
    protected final Hand hand;
    private int coins;

    Player(String nickname) {
        this.nickname = nickname;
        this.hand = new Hand();
        this.coins = INITIAL_COINS;
    }



    public Card getLastCardPicked(){
        return this.hand.getLastCard();
    }

    public void addCard (Card newCard){
        this.hand.addCard(newCard);
    }

    public void discardCards() { hand.discardCards(); }

    public String getNickname() {
        return nickname;
    }

    public int getCoins() {
        return coins;
    }

    public void giveCoins(int amount) {
        coins+=amount;
    }

    public void bet(int amount) throws InsufficientCoinsException{
        if (coins >= amount){
            coins -= amount;
            hand.addBet(amount);
        } else{
            throw new InsufficientCoinsException(nickname + " has " + coins + " coins and tries to bet " + amount + " coins.");
        }
    }

    public int getHandValue() {
        return hand.value();
    }
}

