package ch.supsi.blackjack.model;

public class NewGameCommand implements Command{
    GameHandler receiver;

    public NewGameCommand(GameHandler gameModel){
        this.receiver = gameModel;
    }

    @Override
    public void execute() {
        receiver.newGame();
    }
}
