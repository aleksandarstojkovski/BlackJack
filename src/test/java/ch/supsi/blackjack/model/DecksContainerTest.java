package ch.supsi.blackjack.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecksContainerTest {

    @Test
    void shuffle() {
        try {
            DecksContainer decksContainer1 = new DecksContainer(3);
            DecksContainer decksContainer2 = new DecksContainer(3);
            decksContainer2.shuffle();
        } catch (InvalidDeckContainerSizeException e) {
            fail();
        }
    }
}