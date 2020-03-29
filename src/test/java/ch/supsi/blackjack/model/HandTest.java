package ch.supsi.blackjack.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {
    @Test
    void addCard() {
        Hand testHand = new Hand();
        testHand.addCard(new Card(Seed.C,Value.ACE));
        assertEquals(1,testHand.getCardList().size());
    }

    @Test
    void value() {
        Hand testHand = new Hand();
        testHand.addCard(new Card(Seed.C,Value.ACE));
        assertEquals(10,testHand.value());
        testHand.addCard(new Card(Seed.C,Value.ACE));
        assertEquals(20,testHand.value());
        testHand.addCard(new Card(Seed.C,Value.ACE));
        assertEquals(21
                ,testHand.value());
    }

    @Test
    void discardCards() {
        Hand testHand = new Hand();
        testHand.addCard(new Card(Seed.C,Value.ACE));
        testHand.discardCards();
        assertEquals(0,testHand.getCardList().size());
    }
}