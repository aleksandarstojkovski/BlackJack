package ch.supsi.blackjack.model;

public class ConfirmBetCommand implements Command{
    GameHandler receiver;

    public ConfirmBetCommand(GameHandler gameModel){
        this.receiver = gameModel;
    }

    @Override
    public void execute() {
        receiver.confirmBet();
    }
}
