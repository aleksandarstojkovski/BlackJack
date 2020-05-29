package ch.supsi.blackjack.component;

import ch.supsi.blackjack.model.Card;
import ch.supsi.blackjack.model.Seed;
import ch.supsi.blackjack.model.Value;
import org.junit.Assert;
import org.junit.Test;

public class CardImageTest {

    @Test
    public void getUrl() {
        var cardBuilder = new Card.Builder();
        for (Seed seed : Seed.values()) {
            cardBuilder.setSeed(seed);
            for (Value val : Value.values()) {
                Card card = cardBuilder.setValue(val).build();
                CardImage cardImage = new CardImage(card, false);
                Assert.assertNotNull(cardImage.getUrl());
            }
        }
    }

}