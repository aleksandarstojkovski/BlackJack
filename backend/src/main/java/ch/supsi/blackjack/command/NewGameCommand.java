package ch.supsi.blackjack.command;

import ch.supsi.blackjack.model.GameHandler;

public class NewGameCommand implements Command {

    GameHandler receiver;

    public NewGameCommand(GameHandler gameModel){
        this.receiver = gameModel;
    }

    @Override
    public void execute() {
        receiver.newGame();
    }

}
