package ch.supsi.blackjack.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void equals() {
        Card card1 = new Card.Builder(Value.ACE).setSeed(Seed.D).build();
        Card card2 = new Card.Builder(Value.ACE).setSeed(Seed.D).build();
        assertEquals(card1, card2);
    }

    @Test
    void notEquals() {
        Card card1 = new Card.Builder(Value.ACE).setSeed(Seed.D).build();
        Card card2 = new Card.Builder(Value.TEN).setSeed(Seed.D).build();
        assertNotEquals(card1, card2);
    }

}
