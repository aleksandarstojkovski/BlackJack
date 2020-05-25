package ch.supsi.blackjack.command;

import ch.supsi.blackjack.model.GameHandler;

public class ConfirmBetCommand implements Command {

    private final GameHandler receiver;

    public ConfirmBetCommand(GameHandler gameModel){
        this.receiver = gameModel;
    }

    @Override
    public void execute() {
        receiver.confirmBet();
    }

}
