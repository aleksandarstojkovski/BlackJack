package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Model;

public class BlackJackState implements GameState {

    // singleton
    private static BlackJackState instance = new BlackJackState();

    private BlackJackState() {
    }

    public static BlackJackState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    // business logic and state transition
    @Override
    public void updateState(Model model) {
        model.setCurrentState(BetState.instance());
    }

}