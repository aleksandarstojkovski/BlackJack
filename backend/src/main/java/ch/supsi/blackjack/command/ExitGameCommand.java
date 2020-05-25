package ch.supsi.blackjack.command;

import ch.supsi.blackjack.model.GameHandler;

public class ExitGameCommand implements Command {

    private final GameHandler receiver;

    public ExitGameCommand(GameHandler gameModel){
        this.receiver = gameModel;
    }

    @Override
    public void execute() {
        receiver.exitRound();
    }

}
