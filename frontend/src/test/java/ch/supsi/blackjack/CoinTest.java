package ch.supsi.blackjack;

import junit.framework.TestCase;

public class CoinTest extends TestCase {
    Coin testCoin;
    public void testGetValue() {
        testCoin = new Coin(1000);
        assertEquals(1000,testCoin.getValue());
    }
}