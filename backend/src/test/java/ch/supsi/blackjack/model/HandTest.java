package ch.supsi.blackjack.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HandTest {
    @Test
    public void addCard() {
        Hand testHand = new Hand();
        var card = new Card.Builder(Value.ACE).build();
        testHand.addCard(card);
        assertEquals(1, testHand.size());
    }

    @Test
    public void value() {
        Hand testHand = new Hand();

        var card = new Card.Builder(Value.ACE).setSeed(Seed.C).build();
        testHand.addCard(card);
        assertEquals(11, testHand.value());

        card = new Card.Builder(Value.ACE).setSeed(Seed.C).build();
        testHand.addCard(card);
        assertEquals(12, testHand.value());

        card = new Card.Builder(Value.ACE).setSeed(Seed.C).build();
        testHand.addCard(card);
        assertEquals(13, testHand.value());
    }

    @Test
    public void discardCards() {
        Hand testHand = new Hand();
        var card = new Card.Builder(Value.TWO).build();
        testHand.addCard(card);
        testHand.discardCards();
        assertEquals(0, testHand.size());
    }

    @Test
    void isBusted() {
        Hand testHand = new Hand();

        var eight = new Card.Builder(Value.EIGHT).build();
        testHand.addCard(eight);

        var nine = new Card.Builder(Value.NINE).build();
        testHand.addCard(nine);

        var seven = new Card.Builder(Value.SEVEN).build();
        testHand.addCard(seven);

        assertTrue(testHand.isBusted());
    }

    @Test
    void isBlackJack() {
        Hand testHand = new Hand();
        var ace = new Card.Builder(Value.ACE).build();
        testHand.addCard(ace);
        var ten = new Card.Builder(Value.TEN).build();
        testHand.addCard(ten);
        assertTrue(testHand.isBlackJack());
    }

    @Test
    void size() {
        int cards = 0;
        Hand testHand = new Hand();
        assertEquals(cards++, testHand.size());

        var ace = new Card.Builder(Value.ACE).build();
        testHand.addCard(ace);
        assertEquals(cards++, testHand.size());

        var ace2= new Card.Builder(Value.ACE).build();
        testHand.addCard(ace2);
        assertEquals(cards, testHand.size());
    }
}