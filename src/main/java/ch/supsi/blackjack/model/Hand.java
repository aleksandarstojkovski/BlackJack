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

    public int value() {
        int currentHandValue = 0;
        for (Card card : cardList) {
            if (card.getValue().label.equals("ace")&&currentHandValue>10){
                //ToDo: far scegliere il giocatore per attribuire il valore di asso.
                currentHandValue += 1;
            } else {
                currentHandValue += card.getValue().cardValue;
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
