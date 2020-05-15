package ch.supsi.blackjack.component;

import ch.supsi.blackjack.model.Coin;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

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