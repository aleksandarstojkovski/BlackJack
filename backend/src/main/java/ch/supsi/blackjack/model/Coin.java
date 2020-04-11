package ch.supsi.blackjack.model;

public class Coin {
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
