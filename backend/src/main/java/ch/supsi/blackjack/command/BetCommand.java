package ch.supsi.blackjack.command;

import ch.supsi.blackjack.model.GameHandler;

public class BetCommand implements Command {

    private final GameHandler receiver;
    private int coinValue;
    public BetCommand(GameHandler gameModel){
        this.receiver = gameModel;
    }

    @Override
    public void execute() {
        receiver.bet(coinValue);
    }

    public void execute(int coinValue){
        this.coinValue = coinValue;
        execute();
    }

}
