package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Model;
import ch.supsi.blackjack.model.Player;
import javafx.scene.control.Alert;

public class UpdateTableState implements GameState {

    // singleton
    private static UpdateTableState instance = new UpdateTableState();

    private UpdateTableState() {
    }

    public static UpdateTableState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    // business logic and state transition
    @Override
    public void updateState(Model model) {
        model.setRoundCompleted();

        if (model.hasPlayerMoney()){
            model.setCurrentState(BetState.instance());
        } else {
            model.setGameOver();
            model.setCurrentState(GameOverState.instance());
            model.nextState();
        }

    }

}