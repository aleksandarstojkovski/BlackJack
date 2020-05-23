package ch.supsi.blackjack.command;

import ch.supsi.blackjack.model.GameHandler;

public class StandCommand implements Command {

    GameHandler receiver;

    public StandCommand (GameHandler gameModel){
        this.receiver = gameModel;
    }

    @Override
    public void execute() {
        receiver.stand();
    }

}
