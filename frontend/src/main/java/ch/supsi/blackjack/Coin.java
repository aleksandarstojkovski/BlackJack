package ch.supsi.blackjack;

public class Coin {

    public final static int[] Values = { 100, 200, 300, 400, 500 };

    private final int value;

    public Coin(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Coin{value=" + value + '}';
    }

}
