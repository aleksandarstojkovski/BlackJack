package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Model;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameOverState implements GameState {

    // singleton
    private static GameOverState instance = new GameOverState();

    private GameOverState() {
    }

    public static GameOverState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    // business logic and state transition
    @Override
    public void updateState(Model model) {

        // TODO: temp dialog
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("Ouch! You've lost every penny.");
        alert.setContentText("Don't worry, you can play again :-).");
        alert.showAndWait();

        model.exitGame();
    }

}