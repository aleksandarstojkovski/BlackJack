package ch.supsi.blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> cardList;
    private int betValue;

    public Hand() {
        this.cardList = new ArrayList<>();
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void addCard(Card card){
        cardList.add(card);
    }

    public int value(){
        int currentHandValue = 0;
        for (Card card : cardList){
            if(card.getValue() == Value.ACE && currentHandValue <= 11){
                    currentHandValue = currentHandValue + 10;
            }else{
                currentHandValue = currentHandValue + card.getValue().cardValue;
            }
        }
        return currentHandValue;
    }

    public void discardCards(){
        this.cardList  = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Valore Mano = " + this.value();
    }

    public void addBet(int amount) {
        this.betValue += amount;
    }

}
