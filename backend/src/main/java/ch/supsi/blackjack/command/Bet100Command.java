package ch.supsi.blackjack.command;

import ch.supsi.blackjack.model.GameHandler;

public class Bet100Command implements Command {

    private final GameHandler receiver;

    public Bet100Command(GameHandler gameModel){
        this.receiver = gameModel;
    }

    @Override
    public void execute() {
        receiver.bet(100);
    }

}
