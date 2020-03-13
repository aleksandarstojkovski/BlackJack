package ch.supsi.blackjack.model;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class Deck {

    private Card[] cards;

    Deck(){

        List<Card> cardList = new ArrayList<>();
        for(Seed seed : EnumSet.allOf(Seed.class)) {
            for(Value value : EnumSet.allOf(Value.class)) {
                cardList.add(new Card(seed, value));
            }
        }
        cards = new Card[cardList.size()];
        cardList.toArray(cards);
    }

    public Card[] getCards() {
        return cards;
    }

}
