package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Round;

/**
 * The player was lucky, getting a 21
 * He automatically wins the Round
 * Next State: UpdateTableState
 */
public class RoundBlackJackState implements RoundState {

    public  RoundBlackJackState(){
        printStatus();
    }

    @Override
    public void next(GameStateManager round) {
        round.setState(new RoundUpdateTableState());
    }

    @Override
    public void prev(GameStateManager round) {
        System.out.println("No previous state needed from here");
    }

    @Override
    public void printStatus() {
        System.out.println("Current state: " + this.getClass().toString());
    }

    // business logic and state transition
    @Override
    public void updateState(GameStateManager round) {
        // player made blackjack within first two cards
        round.setPlayerBlackjack();
        next(round);
        round.goNextState();
    }

}