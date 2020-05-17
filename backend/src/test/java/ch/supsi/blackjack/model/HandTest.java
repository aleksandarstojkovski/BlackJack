package ch.supsi.blackjack.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HandTest {
    Hand testHand;

    @BeforeEach
    void setup() {
        testHand = new Hand();
    }

    @Test
    public void addCard() {
        var card = new Card.Builder().build();
        testHand.addCard(card);
        assertEquals(1, testHand.size());
    }

    @Test
    public void value() {
        var cardBuilder = new Card.Builder().setValue(Value.ACE);
        testHand.addCard(cardBuilder.build());
        assertEquals(11, testHand.value());

        testHand.addCard(cardBuilder.build());
        assertEquals(12, testHand.value());

        testHand.addCard(cardBuilder.build());
        assertEquals(13, testHand.value());
    }

    @Test
    public void discardCards() {
        var card = new Card.Builder().build();
        testHand.addCard(card);
        testHand.discardCards();
        assertEquals(0, testHand.size());
    }

    @Test
    void isBusted() {
        var cardBuilder = new Card.Builder();
        var eight = cardBuilder.setValue(Value.EIGHT).build();
        testHand.addCard(eight);

        var nine = cardBuilder.setValue(Value.NINE).build();
        testHand.addCard(nine);

        var seven = cardBuilder.setValue(Value.SEVEN).build();
        testHand.addCard(seven);

        assertTrue(testHand.isBusted());
    }

    @Test
    void isBlackJack() {
        var cardBuilder = new Card.Builder();
        var ace = cardBuilder.setValue(Value.ACE).build();
        testHand.addCard(ace);
        var ten = cardBuilder.setValue(Value.TEN).build();
        testHand.addCard(ten);
        assertTrue(testHand.isBlackJack());
    }

    @Test
    void size() {
        int cards = 0;
        assertEquals(cards++, testHand.size());

        var cardBuilder = new Card.Builder();
        testHand.addCard(cardBuilder.build());
        assertEquals(cards++, testHand.size());

        testHand.addCard(cardBuilder.build());
        assertEquals(cards, testHand.size());
    }
}