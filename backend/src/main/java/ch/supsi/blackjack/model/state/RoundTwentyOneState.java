package ch.supsi.blackjack.model.state;

/**
 * Player asked for new cards (hit) and reached 21
 * It's not enough to win automatically
 * We need to compare with Dealer cards
 * Next State: UpdateTableState
 */
public class RoundTwentyOneState implements RoundState {

    public RoundTwentyOneState() {
        printStatus();
    }

    @Override
    public void next(GameStateManager round) {
        round.setState(new RoundUpdateTableState());
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
        round.setPlayerTwentyOne();
        next(round);
        round.goNextState();
    }

}