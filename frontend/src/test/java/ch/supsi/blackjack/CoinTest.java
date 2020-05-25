package ch.supsi.blackjack;

import ch.supsi.blackjack.model.exception.InvalidCoinValueException;
import junit.framework.TestCase;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CoinTest extends TestCase {

    private Coin testCoin;
    private final Random rand = new Random();

    public void testGetValidCoinValue() {

        int randomNumberInRange = rand.nextInt(Coin.Values.length);
        int randomCoinValue = Coin.Values[randomNumberInRange];

        try {
            testCoin = new Coin(randomCoinValue);
        } catch (InvalidCoinValueException e) {
            fail();
        }

        assertEquals(randomCoinValue,testCoin.getValue());

    }

    public void testGetInvalidCoinValue() {
        assertThrows(InvalidCoinValueException.class, ()->new Coin(-1));
    }

}