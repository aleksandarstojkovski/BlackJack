package ch.supsi.blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {
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
            if (card.getValue().equals(Value.ACE) && currentHandValue > 10){
                //ToDo: far scegliere il giocatore per attribuire il valore di asso.
                currentHandValue += 1;
            } else {
                currentHandValue += card.getValue().cardValue;
            }
        }
        return currentHandValue;
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
        // TODO: manage in better way do not clear array
        cardList.clear();
        return bettedCoins;
    }

    public int size() {
        return cardList.size();
    }

    public int getLastCardValue() {
        return cardList.get(cardList.size()-1).getValue().cardValue;
    }
}
