package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Model;

public class PlayerBustState implements GameState {


    // singleton
    private static PlayerBustState instance = new PlayerBustState();

    private PlayerBustState() {
    }

    public static PlayerBustState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    // business logic and state transition
    @Override
    public void updateState(Model model) {
        model.nextRoundProperty().setValue(true);
        model.playerBustedProperty().setValue(true);
        model.setCurrentState(UpdateTableState.instance());
        model.nextState();
    }

}