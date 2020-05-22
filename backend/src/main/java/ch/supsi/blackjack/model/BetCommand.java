package ch.supsi.blackjack.model;

public class BetCommand implements Command{
    private GameHandler receiver;
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
