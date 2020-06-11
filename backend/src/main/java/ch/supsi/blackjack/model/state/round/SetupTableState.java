package ch.supsi.blackjack.model.state.round;

import ch.supsi.blackjack.model.RoundHandler;

/**
 * Player confirmed his bet and cards was distributed
 * If player got 21 with 2 cards the Round is over, main player won.
 * Next State: BlackJackState,PlayerDealsState
 */
public class SetupTableState implements RoundState {

    private final RoundHandler round;

    public SetupTableState(RoundHandler round){
        this.round = round;
        printStatus();
    }

    @Override
    public void next() {
        round.setState(new PlayerDealsState(round));
    }

    @Override
    public void prev() {
        round.setState(new BetState(round));
    }

    @Override
    public void printStatus() {
        System.out.println("Current state: " + this.getClass().toString());
    }

    // business logic and state transition
    @Override
    public void updateState() {
        if (round.isPlayerHandBlackjack()){
            round.setState(new BlackJackState(round));
            round.goNextState();
        } else {
            next();
        }
    }
}
