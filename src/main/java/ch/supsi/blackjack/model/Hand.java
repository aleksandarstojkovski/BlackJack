package ch.supsi.blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Card> cardList;

    public Hand() {
        this.cardList = new ArrayList<>();
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public int addCardToHand (Card card){
        cardList.add(card);
        return cardList.size();
    }

}
