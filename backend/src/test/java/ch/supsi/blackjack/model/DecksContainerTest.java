package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.exception.InvalidDecksContainerSizeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecksContainerTest {

    DecksContainer.Builder decksBuilder;

    @BeforeEach
    void setup() {
        decksBuilder = new DecksContainer.Builder();
    }

    @Test
    void equals() {
        var decksContainer1 = decksBuilder.build();
        var decksContainer2 = decksBuilder.build();
        assertEquals(decksContainer1, decksContainer2);
    }

    @Test
    void shuffle() {
        var decksContainer1 = decksBuilder.build();
        var decksContainer2 = decksBuilder.build();
        decksContainer1.shuffle();
        assertNotEquals(decksContainer1,decksContainer2);
    }

    @Test
    public void invalidSize(){
        assertThrows(InvalidDecksContainerSizeException.class, () -> decksBuilder.numberOfDecks(-1) );
        assertThrows(InvalidDecksContainerSizeException.class, () -> decksBuilder.numberOfDecks(6) );
    }

    @Test
    public void getCard(){
        DecksContainer dk = decksBuilder.build();
        int initialSize = dk.getAvailableCardsCount();
        dk.getCard();
        dk.getCard();
        assertEquals(initialSize-2, dk.getAvailableCardsCount());
    }

    @Test
    public void size(){
        DecksContainer dk = decksBuilder.build();

        int size = Value.values().length * Seed.values().length * DecksContainer.DEFAULT_NUMBER_OF_DECKS;
        assertEquals(size, dk.getAvailableCardsCount());
    }
}