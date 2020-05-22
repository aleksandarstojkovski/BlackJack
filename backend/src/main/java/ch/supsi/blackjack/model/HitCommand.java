package ch.supsi.blackjack.model;

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
