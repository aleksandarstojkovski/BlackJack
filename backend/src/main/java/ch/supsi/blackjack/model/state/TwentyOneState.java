package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Model;

public class TwentyOneState implements GameState {

    // singleton
    private static TwentyOneState instance = new TwentyOneState();

    private TwentyOneState() {
    }

    public static TwentyOneState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    // business logic and state transition
    @Override
    public void updateState(Model model) {
        model.setPlayerTwentyone();
        model.setCurrentState(UpdateTableState.instance());
        model.nextState();
    }

}