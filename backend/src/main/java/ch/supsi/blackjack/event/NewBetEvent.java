package ch.supsi.blackjack.event;

public class NewBetEvent extends AbstractEvent {

    private final int betValue;

    public NewBetEvent(Object source, int betValue) {
        super(source);
        this.betValue=betValue;
    }

    public int getBetValue() {
        return betValue;
    }

}
