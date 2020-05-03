package ch.supsi.blackjack.model.state;

/**
 * This is the very initial state
 * Next state: BetState
 */
public class RoundInitState implements RoundState {

    public RoundInitState(){
        printStatus();
    }
    @Override
    public void next(GameStateManager round) {
        round.setState(new RoundBetState());
    }

    @Override
    public void prev(GameStateManager round) {
        System.out.println("No previous state from here");
    }

    @Override
    public void printStatus() {
        System.out.println("Current state: " + this.getClass().toString());
    }

    // business logic and state transition
    @Override
    public void updateState(GameStateManager round) {
        next(round);
    }

}