package ch.supsi.blackjack.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    Deck testDeck = new Deck();

    @Test
    void deckValidation(){
        assertNotNull(testDeck);
        assertEquals(52,testDeck.getCards().length);
        for(Card card : testDeck.getCards()){
            System.out.println(card);
        }
    }
}