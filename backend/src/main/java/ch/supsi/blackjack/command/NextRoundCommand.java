package ch.supsi.blackjack.command;

import ch.supsi.blackjack.model.GameHandler;

public class NextRoundCommand implements Command {

    GameHandler receiver;

    public NextRoundCommand(GameHandler gameModel){
        this.receiver = gameModel;
    }

    @Override
    public void execute() {
        receiver.nextRound();
    }

}
