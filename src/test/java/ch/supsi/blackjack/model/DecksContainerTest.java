package ch.supsi.blackjack.model;

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

}