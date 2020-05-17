package ch.supsi.blackjack.model.state.round;

import ch.supsi.blackjack.model.RoundHandler;

/**
 * This is the turn of the Dealer.
 * He can hit or stand.
 * In case of Bust the Round is over, players won
 * In case of Stand, cards are compared against the players
 * Next State: DealerBustState,UpdateTableState
*/
public class DealerDealsState implements RoundState {

    private final RoundHandler round;

    public DealerDealsState(RoundHandler round) {
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
        if(round.getDealerHand().isBusted()){
            round.setState(new DealerBustState(round));
        } else {
            next();
        }
        round.goNextState();
    }
}
