package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.exception.InvalidDecksContainerSizeException;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DecksContainerTest {

    @Test
    void equals() {
        DecksContainer decksContainer1;
        DecksContainer decksContainer2;
        try {
            decksContainer1 = new DecksContainer(3);
            decksContainer2 = new DecksContainer(3);
            assertEquals(decksContainer1, decksContainer2);
        } catch (InvalidDecksContainerSizeException e) {
            fail();
        }
    }

    @Test
    void shuffle() {
        DecksContainer decksContainer1;
        DecksContainer decksContainer2;
        try {
            decksContainer1 = new DecksContainer(3);
            decksContainer2 = new DecksContainer(3);
            decksContainer1.shuffle();
            assertNotEquals(decksContainer1,decksContainer2);
        } catch (InvalidDecksContainerSizeException e) {
            fail();
        }
    }

    @Test
    public void invalidSize(){
        InvalidDecksContainerSizeException thrown1 = assertThrows(InvalidDecksContainerSizeException.class, ()->new DecksContainer(-1));
        InvalidDecksContainerSizeException thrown2 = assertThrows(InvalidDecksContainerSizeException.class, ()->new DecksContainer(6));
    }

    @Test
    public void getCard(){
        DecksContainer dk = null;
        try {
            dk = new DecksContainer(3);
        } catch (InvalidDecksContainerSizeException e) {
            fail();
        }
        int initialSize = dk.getAvailableCardsCount();
        dk.getCard();
        dk.getCard();
        assertEquals(initialSize-2,dk.getAvailableCardsCount());
    }

    @Test
    public void size(){
        DecksContainer dk = null;
        int numberOfDecks = 3;
        try {
            dk = new DecksContainer(numberOfDecks);
        } catch (InvalidDecksContainerSizeException e) {
            fail();
        }
        assertEquals(52*numberOfDecks,dk.getAvailableCardsCount());
    }

}