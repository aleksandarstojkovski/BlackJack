package ch.supsi.blackjack.command;

import ch.supsi.blackjack.model.GameHandler;

public class Bet200Command implements Command {

    private final GameHandler receiver;

    public Bet200Command(GameHandler gameModel){
        this.receiver = gameModel;
    }

    @Override
    public void execute() {
        receiver.bet(200);
    }

}
