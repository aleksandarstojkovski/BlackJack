package ch.supsi.blackjack.model;

public class ExitGameCommand implements Command{
    GameHandler receiver;

    public ExitGameCommand(GameHandler gameModel){
        this.receiver = gameModel;
    }

    @Override
    public void execute() {
        receiver.exitRound();
    }
}
