package ch.supsi.blackjack.command;

import ch.supsi.blackjack.model.GameHandler;

public class HitCommand implements Command {

    GameHandler receiver;

    public HitCommand (GameHandler gameModel){
        this.receiver = gameModel;
    }

    @Override
    public void execute() {
        receiver.hit();
    }

}
