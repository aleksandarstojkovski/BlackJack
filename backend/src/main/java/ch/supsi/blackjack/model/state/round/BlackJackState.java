package ch.supsi.blackjack.model.state.round;

import ch.supsi.blackjack.model.RoundHandler;

/**
 * The player was lucky, getting a 21
 * He automatically wins the Round
 * Next State: UpdateTableState
 */
public class BlackJackState implements RoundState {

    RoundHandler round;

    public BlackJackState(RoundHandler round){
        this.round =  round;
        printStatus();
    }

    @Override
    public void next() {
        round.setState(new UpdateTableState(round));
    }

    @Override
    public void prev() {
        System.out.println("No previous state needed from here");
    }

    @Override
    public void printStatus() {
        System.out.println("Current state: " + this.getClass().toString());
    }

    // business logic and state transition
    @Override
    public void updateState() {
        // player made blackjack within first two cards
        round.setPlayerBlackjack();
        next();
        round.goNextState();
    }

}