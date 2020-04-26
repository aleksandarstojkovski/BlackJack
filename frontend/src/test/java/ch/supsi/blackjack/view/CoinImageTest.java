package ch.supsi.blackjack.view;

import ch.supsi.blackjack.model.Coin;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CoinImageTest {
    @BeforeAll
    public static void beforeClass() {
        // WORKAROUND !!!
        // initialize javafx toolkit
        JFXPanel fxPanel = new JFXPanel();
    }

    @Test
    void getImage() {
        Coin coin = new Coin(5);
        CoinImage img = new CoinImage(coin);
        assertNotNull(img.getImage());
    }
}