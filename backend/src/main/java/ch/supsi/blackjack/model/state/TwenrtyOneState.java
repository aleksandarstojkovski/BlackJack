package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Model;

public class TwenrtyOneState implements GameState {

    // singleton
    private static TwenrtyOneState instance = new TwenrtyOneState();

    private TwenrtyOneState() {
    }

    public static TwenrtyOneState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    // business logic and state transition
    @Override
    public void updateState(Model model) {
        model.nextRoundProperty().set(true);
        model.setCurrentState(UpdateTableState.instance());
        model.nextState();
    }

}