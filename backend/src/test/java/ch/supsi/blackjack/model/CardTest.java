package ch.supsi.blackjack.model;

import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void getFileName() {
        Card card = new Card(Seed.D, Value.ACE);
        String fileName = card.getFileName();
        assertEquals("/ch/supsi/blackjack/images/cards/ace_of_diamonds.png", fileName);

//        URL url = Card.class.getResource(fileName);
//        assertNotNull(url);
    }
}