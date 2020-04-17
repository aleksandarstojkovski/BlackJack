package ch.supsi.blackjack.view;

import ch.supsi.blackjack.model.Coin;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CoinImageTest {
    @Test
    void getFileName() {
        Coin coin = new Coin(5);
        CoinImage img = new CoinImage(coin);

        String fileName = img.getFileName();
        assertEquals("/ch/supsi/blackjack/images/coins/5.png", fileName);

        URL url = CoinImage.class.getResource(fileName);
        assertNotNull(url);
    }
}