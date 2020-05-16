package ch.supsi.blackjack.model;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class Deck {

    private final Card[] cards;

    Deck() {
        this(Card.BackColor.BLUE);
    }

    Deck(Card.BackColor backColor) {
        List<Card> cardList = new ArrayList<>();

        for(Value value : EnumSet.allOf(Value.class)) {
            var cardBuilder = new Card.Builder(value).setBack(backColor);
            for(Seed seed : EnumSet.allOf(Seed.class)) {
                Card card = cardBuilder.setSeed(seed).build();
                cardList.add(card);
            }
        }
        cards = new Card[cardList.size()];
        cardList.toArray(cards);
    }

    public Card[] getCards() {
        return cards;
    }

}
