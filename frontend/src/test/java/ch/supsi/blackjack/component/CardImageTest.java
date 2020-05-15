package ch.supsi.blackjack.component;

import ch.supsi.blackjack.model.Card;
import ch.supsi.blackjack.model.Seed;
import ch.supsi.blackjack.model.Value;
import org.junit.Assert;

public class CardImageTest {
    @org.junit.Test
    public void getUrl() {
        for (Seed seed : Seed.values()) {
            for (Value val : Value.values()) {
                Card card = new Card(seed, val, "blu");
                CardImage cardImage = new CardImage(card, false);
                Assert.assertNotNull(cardImage.getUrl());
            }
        }
    }
}