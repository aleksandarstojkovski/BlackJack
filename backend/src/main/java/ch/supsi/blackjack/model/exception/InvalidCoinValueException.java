package ch.supsi.blackjack.model.exception;

public class InvalidCoinValueException extends Throwable {
    public InvalidCoinValueException(String msg){
        super(msg);
    }
}
