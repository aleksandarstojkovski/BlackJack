package ch.supsi.blackjack.component;

import ch.supsi.blackjack.model.Card;
import ch.supsi.blackjack.model.Seed;
import ch.supsi.blackjack.model.Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CardImageTest {
    @Test
    void getUrl() {
        for (Seed seed : Seed.values()) {
            for (Value val : Value.values()) {
                Card card = new Card(seed, val, "blu");
                CardImage cardImage = new CardImage(card, false);
                assertNotNull(cardImage.getUrl());
            }
        }
    }
}