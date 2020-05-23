package ch.supsi.blackjack.command;

import ch.supsi.blackjack.model.GameHandler;

public class Bet400Command implements Command {

    private final GameHandler receiver;

    public Bet400Command(GameHandler gameModel){
        this.receiver = gameModel;
    }

    @Override
    public void execute() {
        receiver.bet(400);
    }

}
