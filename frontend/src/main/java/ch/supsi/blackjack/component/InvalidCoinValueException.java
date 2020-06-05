package ch.supsi.blackjack.component;

public class InvalidCoinValueException extends Throwable {
    public InvalidCoinValueException(String msg){
        super(msg);
    }
}
