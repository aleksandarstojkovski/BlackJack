package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Model;

public class InitState implements GameState {

    // singleton
    private static InitState instance = new InitState();

    private InitState() {
    }

    public static InitState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    // business logic and state transition
    @Override
    public void updateState(Model model) {
        model.gameRunningProperty().setValue(true);
        model.setCurrentState(BetState.instance());
    }

}