package ch.supsi.blackjack.model.state;

/**
 * This is the end of all Rounds
 * Players don't have any more money or simply don't wont to play more
 * Next State: InitState
 */
public class RoundGameOverState implements RoundState {

    public RoundGameOverState() {
        printStatus();
    }

    @Override
    public void next(GameStateManager round) {
        round.setState(new RoundInitState());
    }

    @Override
    public void prev(GameStateManager round) {

    }

    @Override
    public void printStatus() {
        System.out.println("Current state: " + this.getClass().toString());
    }

    // business logic and state transition
    @Override
    public void updateState(GameStateManager round) {
        next(round);
        round.exitGame();
    }

}