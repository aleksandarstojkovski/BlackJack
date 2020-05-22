package ch.supsi.blackjack.model;

public class NextRoundCommand implements Command{
    GameHandler receiver;

    public NextRoundCommand(GameHandler gameModel){
        this.receiver = gameModel;
    }

    @Override
    public void execute() {
        receiver.nextRound();
    }
}
