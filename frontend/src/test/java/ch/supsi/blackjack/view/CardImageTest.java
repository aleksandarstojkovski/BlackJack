package ch.supsi.blackjack.view;

import ch.supsi.blackjack.model.Card;
import ch.supsi.blackjack.model.Seed;
import ch.supsi.blackjack.model.Value;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class CardImageTest {
    @BeforeAll
    public static void beforeClass() {
        // WORKAROUND !!!
        // initialize javafx toolkit
        JFXPanel fxPanel = new JFXPanel();
    }

    @Test
    void getImage() {
        for (Seed seed : Seed.values()) {
            for (Value val : Value.values()) {
                Card card = new Card(seed, val, "blu");
                CardImage cardImage = new CardImage(card, false);
                assertNotNull(cardImage.getImage());
            }
        }
    }
}