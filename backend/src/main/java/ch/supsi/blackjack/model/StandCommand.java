package ch.supsi.blackjack.model;

public class StandCommand implements Command{
    GameHandler receiver;

    public StandCommand (GameHandler gameModel){
        this.receiver = gameModel;
    }

    @Override
    public void execute() {
        receiver.stand();
    }
}
