package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Model;

public class PlayerBurstState implements GameState {


    // singleton
    private static PlayerBurstState instance = new PlayerBurstState();

    private PlayerBurstState() {
    }

    public static PlayerBurstState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    // business logic and state transition
    @Override
    public void updateState(Model model) {
        model.setCurrentState(UpdateTableState.instance());
    }

}