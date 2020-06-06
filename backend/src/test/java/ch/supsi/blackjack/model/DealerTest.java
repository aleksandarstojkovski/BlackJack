package ch.supsi.blackjack.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DealerTest {

    private final int initialCoins = 1000;
    private String nickName = "TestDealer";

    @Test
    void testFirstConstructor() {
        Dealer dealer = new Dealer(nickName, initialCoins);
        assertNotNull(dealer);
    }

    @Test
    void testSecondConstructor() {
        Dealer dealer = new Dealer(nickName);
        assertNotNull(dealer);
    }

}
