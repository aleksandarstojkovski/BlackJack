package ch.supsi.blackjack.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DealerAITest {

    private  final Dealer dealer = new Dealer("dealer");

    @Test
    void testConstructor() {
        DealerAI dealerAI = new DealerAI(dealer);
        assertNotNull(dealerAI);
    }

}

