package ch.supsi.blackjack.command;

import ch.supsi.blackjack.model.GameHandler;

public class BetCommand implements Command {

    private final int bet;

    private final GameHandler receiver;

    public BetCommand(GameHandler gameModel, int bet){
        this.receiver = gameModel;
        this.bet = bet;
    }

    @Override
    public void execute() {
        receiver.bet(bet);
    }

}
