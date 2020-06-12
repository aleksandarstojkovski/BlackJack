package ch.supsi.blackjack.component;

import ch.supsi.blackjack.component.Coin;
import ch.supsi.blackjack.component.InvalidCoinValueException;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CoinTest extends TestCase {
    @Test
    public void testGetValidCoinValue() throws InvalidCoinValueException {
        for (int coinvalue : Coin.Values) {
            Coin coin = new Coin(coinvalue);
            assertEquals(coinvalue, coin.getValue());
        }
    }

    @Test
    public void testGetInvalidCoinValue() {
        assertThrows(InvalidCoinValueException.class, ()->new Coin(-1));
    }

    @Test
    public void testCoinsAmnt(){
        assertEquals(Coin.Values.length, 5);
    }

}