package ch.supsi.blackjack.view;

import ch.supsi.blackjack.model.Coin;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CoinImageTest {
    @Test
    void getUrl() {
        for (int val : Coin.Values) {
            Coin coin = new Coin(val);
            CoinImage img = new CoinImage(coin);
            assertNotNull(img.getUrl());
        }
    }
}