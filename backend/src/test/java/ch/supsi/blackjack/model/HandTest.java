package ch.supsi.blackjack.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandTest {
    @Test
    public void addCard() {
        Hand testHand = new Hand();
        testHand.addCard(new Card(Seed.C,Value.ACE));
        assertEquals(1,testHand.getCardList().size());
    }

    @Test
    public void value() {
        Hand testHand = new Hand();

        testHand.addCard(new Card(Seed.C,Value.ACE));
        assertEquals(11, testHand.value());

        testHand.addCard(new Card(Seed.C,Value.ACE));
        assertEquals(12, testHand.value());

        testHand.addCard(new Card(Seed.C,Value.ACE));
        assertEquals(13, testHand.value());
    }

    @Test
    public void discardCards() {
        Hand testHand = new Hand();
        testHand.addCard(new Card(Seed.C,Value.ACE));
        testHand.discardCards();
        assertEquals(0,testHand.getCardList().size());
    }
}