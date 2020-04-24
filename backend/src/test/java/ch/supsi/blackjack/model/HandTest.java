package ch.supsi.blackjack.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandTest {
    @Test
    public void addCard() {
        Hand testHand = new Hand();
        testHand.addCard(new Card(Seed.C,Value.ACE,"blu"));
        assertEquals(1,testHand.size());
    }

    @Test
    public void value() {
        Hand testHand = new Hand();

        testHand.addCard(new Card(Seed.C,Value.ACE,"blu"));
        assertEquals(11, testHand.value());

        testHand.addCard(new Card(Seed.C,Value.ACE,"blu"));
        assertEquals(12, testHand.value());

        testHand.addCard(new Card(Seed.C,Value.ACE,"blu"));
        assertEquals(13, testHand.value());
    }

    @Test
    public void discardCards() {
        Hand testHand = new Hand();
        testHand.addCard(new Card(Seed.C,Value.ACE,"blu"));
        testHand.discardCards();
        assertEquals(0, testHand.size());
    }
}