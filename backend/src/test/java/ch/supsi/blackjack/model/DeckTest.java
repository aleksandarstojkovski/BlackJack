package ch.supsi.blackjack.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    Deck testDeck;

    @BeforeEach
    void setup() {
        testDeck = new Deck.Builder().build();
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
        List<Card> cards = new ArrayList<>();

        var cardBuilder = new Card.Builder();
        for(Value value : EnumSet.allOf(Value.class)) {
            cardBuilder.setValue(value);
            for(Seed seed : EnumSet.allOf(Seed.class)) {
                var card = cardBuilder.setSeed(seed).build();
                cards.add(card);
            }
        }

        var deckCards = Arrays.asList(testDeck.getCards());
        assertTrue(deckCards.containsAll(cards));
    }

    @Test
    void testSmallDeck(){
        testDeck = new Deck.Builder()
                .setValues(EnumSet.range(Value.ACE, Value.TEN))
                .build();

        var cardBuilder = new Card.Builder();
        List<Card> excludedCards = new ArrayList<>();
        for(Value value : EnumSet.range(Value.JACK, Value.KING)) {
            cardBuilder.setValue(value);
            for(Seed seed : EnumSet.allOf(Seed.class)) {
                var card = cardBuilder.setSeed(seed).build();
                excludedCards.add(card);
            }
        }

        var deckCards = Arrays.asList(testDeck.getCards());
        assertFalse(deckCards.containsAll(excludedCards));
    }

}