package ch.supsi.blackjack;

import ch.supsi.blackjack.model.exception.InvalidCoinValueException;
import java.util.Arrays;

public class Coin {

    public final static int[] Values = { 100, 200, 300, 400, 500 };

    private final int value;

    public Coin(int value) throws InvalidCoinValueException {
        if (Arrays.stream(Values).anyMatch(x -> x == value))
            this.value = value;
        else
            throw new InvalidCoinValueException("The chosen coin value: " + value + " is not in: "+ Arrays.toString(Values));
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Coin{value=" + value + '}';
    }

}
