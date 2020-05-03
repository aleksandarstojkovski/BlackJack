package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Round;

/**
 * This is the initial phase of a Round.
 * The player has to bet.
 * Once confirmed the cards can be distributed
 * Next State: BetState, SetupTableState
 */
public class RoundBetState implements RoundState {

    public RoundBetState(){
        printStatus();
    }

    // business logic and state transition
    @Override
    public void updateState(GameStateManager round) {
        // bet confirmed
        if (round.isBetConfirmed()){
            round.openRound();
            next(round);
        }
    }

    @Override
    public void next(GameStateManager round) {
        round.setState(new RoundSetupTableState());
    }

    @Override
    public void prev(GameStateManager round) {
        round.setState(new RoundInitState());
    }

    @Override
    public void printStatus() {
        System.out.println("Current state: " + this.getClass().toString());
    }
}