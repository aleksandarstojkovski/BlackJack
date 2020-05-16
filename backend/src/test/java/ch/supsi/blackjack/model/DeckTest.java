package ch.supsi.blackjack.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    Deck testDeck;

    @BeforeEach
    void setup() {
        testDeck = new Deck();
    }

    @Test
    void testSize(){
        int size = Value.values().length * Seed.values().length;
        assertEquals(size, testDeck.getCards().length);

    }

    @Test
    void testUniqueness(){
        Set<Card> uniqueCards = new HashSet<>(Arrays.asList(testDeck.getCards()));
        assertEquals(testDeck.getCards().length, uniqueCards.size());
    }

    @Test
    void testDeckContainsAllCards(){
        Deck deck = new Deck();
        for(Value value : EnumSet.allOf(Value.class)) {
            for(Seed seed : EnumSet.allOf(Seed.class)) {
                var cardBuilder = new Card.Builder(value).setSeed(seed);
                assertTrue(Arrays.asList(deck.getCards()).contains(cardBuilder.build()));
            }
        }
    }

}