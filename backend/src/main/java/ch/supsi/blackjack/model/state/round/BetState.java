package ch.supsi.blackjack.model.state.round;

import ch.supsi.blackjack.model.RoundHandler;

/**
 * This is the initial phase of a Round.
 * The player has to bet.
 * Once confirmed the cards can be distributed
 * Next State: BetState, SetupTableState
 */
public class BetState implements RoundState {

    RoundHandler round;

    public BetState(RoundHandler round){
        printStatus();
        this.round = round;
    }

    // business logic and state transition
    @Override
    public void updateState() {
        // bet confirmed
        if (round.isBetConfirmed()){
            round.openRound();
            next();
        }
    }

    @Override
    public void next() {
        round.setState(new SetupTableState(round));
    }

    @Override
    public void prev() {
        System.out.println("No previous state from here");
    }

    @Override
    public void printStatus() {
        System.out.println("Current state: " + this.getClass().toString());
    }
}