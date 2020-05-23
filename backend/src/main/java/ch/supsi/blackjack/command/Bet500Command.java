package ch.supsi.blackjack.command;

import ch.supsi.blackjack.model.GameHandler;

public class Bet500Command implements Command {

    private final GameHandler receiver;

    public Bet500Command(GameHandler gameModel){
        this.receiver = gameModel;
    }

    @Override
    public void execute() {
        receiver.bet(500);
    }

}
