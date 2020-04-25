package ch.supsi.blackjack.model;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class Deck {
    private final Card[] cards;

    Deck(){
        List<Card> cardList = new ArrayList<>();
        String back;
        if(Math.random() < 0.5){
            back="red";
        }else{
            back="blu";
        }
        for(Seed seed : EnumSet.allOf(Seed.class)) {
            for(Value value : EnumSet.allOf(Value.class)) {
                cardList.add(new Card(seed, value, back));
            }
        }
        cards = new Card[cardList.size()];
        cardList.toArray(cards);
    }

    public Card[] getCards() {
        return cards;
    }

}
