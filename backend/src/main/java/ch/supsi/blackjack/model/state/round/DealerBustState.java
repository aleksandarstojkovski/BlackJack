package ch.supsi.blackjack.model.state.round;

import ch.supsi.blackjack.model.RoundHandler;

/**
 * Dealer decided to hit, but he exceeds the limit of 21.
 * The Round is over, main player won.
 * Next State: UpdateTableState
 */
public class DealerBustState implements RoundState {

    RoundHandler round;

    public DealerBustState(RoundHandler round) {
        this.round = round;
        printStatus();
    }

    @Override
    public void next() {
       round.setState(new UpdateTableState(round));
    }

    @Override
    public void prev() {

    }

    @Override
    public void printStatus() {
        System.out.println("Current state: " + this.getClass().toString());
    }

    // business logic and state transition
    @Override
    public void updateState() {
        // dealer bust property
        round.setDealerBusted();
        next();
        round.goNextState();
    }

}