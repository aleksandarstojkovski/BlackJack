package ch.supsi.blackjack.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void equals() {
        var builder = new Card.Builder();
        Card card1 = builder.build();
        Card card2 = builder.build();
        assertEquals(card1, card2);
    }

    @Test
    void notEquals() {
        var builder = new Card.Builder().setSeed(Seed.D);
        Card card1 = builder.setValue(Value.ACE).build();
        Card card2 = builder.setValue(Value.TEN).build();
        assertNotEquals(card1, card2);
    }

}
