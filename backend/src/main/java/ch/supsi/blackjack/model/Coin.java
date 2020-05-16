package ch.supsi.blackjack.model;

public class Coin {

    public final static int[] Values = { 100, 200, 300, 400, 500 };
    public final static String[] coinFxIds = { "#coin100", "#coin200", "#coin300", "#coin400", "#coin500" };

    private final int value;
    private final String fxId;

    public Coin(int value){
        this.value = value;
        this.fxId = "coin"+value;
    }

    public int getValue() {
        return value;
    }

    public String getFxId() {
        return fxId;
    }

    @Override
    public String toString() {
        return "Coin{value=" + value + '}';
    }

}
