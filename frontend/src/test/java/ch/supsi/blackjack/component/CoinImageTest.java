package ch.supsi.blackjack.component;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.fail;

public class CoinImageTest {

    @Test
    public void getUrl() {
        for (int val : Coin.Values) {
            Coin coin = null;
            try {
                coin = new Coin(val);
            } catch (InvalidCoinValueException e) {
                fail();
            }
            CoinImage img = new CoinImage(coin);
            Assert.assertNotNull(img.getUrl());
        }
    }

}