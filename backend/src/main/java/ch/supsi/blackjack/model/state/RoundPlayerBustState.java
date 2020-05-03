package ch.supsi.blackjack.model.state;

/**
 * Player decided to hit, but he exceeds the limit of 21.
 * The Round is over, main player won.
 * Next State: UpdateTableState
 */
public class RoundPlayerBustState implements RoundState {

    public RoundPlayerBustState() {
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
        round.setPlayerBusted();
        next(round);
        round.goNextState();
    }

}