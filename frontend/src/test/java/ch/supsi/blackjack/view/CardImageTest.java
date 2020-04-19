package ch.supsi.blackjack.view;

import ch.supsi.blackjack.model.Card;
import ch.supsi.blackjack.model.Seed;
import ch.supsi.blackjack.model.Value;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class CardImageTest {
    @Test
    void getFileName() {
        Card card = new Card(Seed.D, Value.ACE,"blu");
        CardImage cardImage = new CardImage(card,false);

        String fileName = cardImage.getFileName();
        assertEquals("/ch/supsi/blackjack/images/cards/ace_of_diamonds.png", fileName);

        URL url = CardImage.class.getResource(fileName);
        assertNotNull(url);
    }
}