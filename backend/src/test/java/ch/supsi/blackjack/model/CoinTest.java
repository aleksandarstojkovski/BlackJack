package ch.supsi.blackjack.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoinTest {

    Coin testCoin;

    @Test
    void getValue() {
        testCoin = new Coin(1000);
        assertEquals(1000,testCoin.getValue());
    }

    @Test
    void availableCoins() {
        assertEquals(Coin.Values.length,5);
    }

}