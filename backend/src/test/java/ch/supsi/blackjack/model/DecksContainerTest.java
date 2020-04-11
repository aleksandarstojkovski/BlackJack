package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.exception.InvalidDecksContainerSizeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecksContainerTest {

    @Test
    void shuffle() {
        // test equality
        try {
            DecksContainer decksContainer1 = new DecksContainer(3);
            DecksContainer decksContainer2 = new DecksContainer(3);
            assertEquals(decksContainer1,decksContainer2);
        } catch (InvalidDecksContainerSizeException e) {
            fail();
        }
        // test non-equality
        try {
            DecksContainer decksContainer1 = new DecksContainer(3);
            DecksContainer decksContainer2 = new DecksContainer(3);
            decksContainer2.shuffle();
            assertNotEquals(decksContainer1,decksContainer2);
        } catch (InvalidDecksContainerSizeException e) {
            fail();
        }
    }

    @Test
    public void decksContainerException(){
        InvalidDecksContainerSizeException thrown = assertThrows(InvalidDecksContainerSizeException.class, ()->new DecksContainer(-1));
    }

    @Test
    public void getCard(){
        DecksContainer dk = null;
        try {
            dk = new DecksContainer(3);
        } catch (InvalidDecksContainerSizeException e) {
            fail();
        }
        int initialSize= dk.getAvailableCardsCount();
        dk.getCard();
        dk.getCard();
        assertEquals(initialSize-2,dk.getAvailableCardsCount());
    }

}