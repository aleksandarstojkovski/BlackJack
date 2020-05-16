package ch.supsi.blackjack.model.state.round;

import ch.supsi.blackjack.model.RoundHandler;

/**
 * Player asked for new cards (hit) and reached 21
 * It's not enough to win automatically
 * We need to compare with Dealer cards
 * Next State: UpdateTableState
 */
public class TwentyOneState implements RoundState {

    RoundHandler round;

    public TwentyOneState(RoundHandler round) {
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
        round.setPlayerTwentyOne();
        next();
        round.goNextState();
    }

}