package ch.supsi.blackjack.model.state.round;

import ch.supsi.blackjack.model.RoundHandler;

/**
 * The Round is completed
 * If player has more money he can still play
 * Otherwise the game is over
 * Next State: BetState,GameOverState
 */

public class UpdateTableState implements RoundState {

    private final RoundHandler round;

    public UpdateTableState(RoundHandler round) {
        this.round = round;
        printStatus();
    }

    @Override
    public void next() {
        round.setState(new BetState(round));
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
        round.setRoundCompleted();

        if (round.isPlayerWithMoney()){
            next();
        } else {
            round.setGameOver();
            round.setState(new GameOverState(round));
            round.goNextState();
        }
    }

}