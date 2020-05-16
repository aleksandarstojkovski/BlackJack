package ch.supsi.blackjack.model.state.round;

import ch.supsi.blackjack.model.GameModel;
import ch.supsi.blackjack.model.Round;
import ch.supsi.blackjack.model.RoundHandler;

/**
 * This is the end of all Rounds
 * Players don't have any more money or simply don't wont to play more
 * Next State: InitState
 */
public class GameOverState implements RoundState {

    RoundHandler round;

    public GameOverState(RoundHandler round) {
        this.round = round;
        printStatus();
    }

    @Override
    public void next() {
        round.exitRound();
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
        next();
    }

}