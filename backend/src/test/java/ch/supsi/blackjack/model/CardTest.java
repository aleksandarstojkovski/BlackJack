package ch.supsi.blackjack.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    Card.Builder builder;

    @BeforeEach
    void setup() {
        builder = new Card.Builder();
    }

    @Test
    void seed() {
        Card card = builder.setSeed(Seed.D).build();
        assertEquals(Seed.D, card.getSeed());
    }

    @Test
    void value() {
        Card card = builder.setValue(Value.TEN).build();
        assertEquals(Value.TEN, card.getValue());
    }

    @Test
    void defaultValue() {
        Card card = builder.setValue(Value.KING).build();
        assertEquals(Value.KING.getDefaultValue(), card.getDefaultValue());
    }

    @Test
    void back() {
        Card card = builder.setBack(Card.BackColor.BLUE).build();
        assertEquals(Card.BackColor.BLUE, card.getBack());
    }

    @Test
    void equals() {
        Card card1 = builder.setValue(Value.ACE).build();
        Card card2 = builder.setValue(Value.ACE).build();
        assertEquals(card1, card2);

        assertEquals(card1.hashCode(), card2.hashCode());
    }

    @Test
    void notEquals() {
        var builder = new Card.Builder().setSeed(Seed.D);
        Card card1 = builder.setValue(Value.ACE).build();
        Card card2 = builder.setValue(Value.TEN).build();
        assertNotEquals(card1, card2);
    }

}
