package ch.supsi.blackjack.command;

import ch.supsi.blackjack.model.GameHandler;

public class Bet300Command implements Command {

    private final GameHandler receiver;

    public Bet300Command(GameHandler gameModel){
        this.receiver = gameModel;
    }

    @Override
    public void execute() {
        receiver.bet(300);
    }

}
