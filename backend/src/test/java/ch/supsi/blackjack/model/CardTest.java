package ch.supsi.blackjack.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void equals() {
        Card card1 = new Card(Seed.D, Value.ACE);
        Card card2 = new Card(Seed.D, Value.ACE);
        assertEquals(card1, card2);
    }
}
