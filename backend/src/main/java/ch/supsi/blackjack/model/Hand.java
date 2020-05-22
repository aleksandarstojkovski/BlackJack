package ch.supsi.blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    public final static int BLACKJACK = 21;
    private final List<Card> cardList;
    private int betValue;

    public Hand() {
        this.cardList = new ArrayList<>();
    }

    public void addCard(Card card){
        cardList.add(card);
    }

    public int value() {
        int currentHandValue = 0;
        for (Card card : cardList) {
            currentHandValue = getCardValue(currentHandValue, card);
        }
        return currentHandValue;
    }

    private int getCardValue(int currentHandValue, Card card) {
        if (card.getValue().equals(Value.ACE) && currentHandValue > 10){
            //ToDo: far scegliere al giocatore il valore da attribuire all'asso.
            currentHandValue += 1;
        } else {
            currentHandValue += card.getValue().getDefaultValue();
        }
        return currentHandValue;
    }

    public Card getLastCard(){
        return cardList.get(cardList.size()-1);
    }

    public void discardCards(){
        cardList.clear();
    }

    @Override
    public String toString() {
        return "Valore Mano = " + this.value();
    }

    public void addBet(int amount) {
        this.betValue += amount;
    }

    public int takeBets(){
        int bettedCoins = betValue;
        betValue=0;
        discardCards();
        return bettedCoins;
    }

    public int size() {
        return cardList.size();
    }

    public boolean isBusted() {
        return value() > BLACKJACK;
    }

    public boolean isBlackJack() {
        return value() == BLACKJACK;
    }
}
