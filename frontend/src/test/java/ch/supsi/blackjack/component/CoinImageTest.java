package ch.supsi.blackjack.component;

import ch.supsi.blackjack.model.Coin;
import org.junit.Assert;
import org.junit.Test;

public class CoinImageTest {
    @Test
    public void getUrl() {
        for (int val : Coin.Values) {
            Coin coin = new Coin(val);
            CoinImage img = new CoinImage(coin);
            Assert.assertNotNull(img.getUrl());
        }
    }
}