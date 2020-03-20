package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.event.ExitGameEvent;
import ch.supsi.blackjack.event.NewGameEvent;
import ch.supsi.blackjack.model.AbstractModel;
import ch.supsi.blackjack.model.Model;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.beans.PropertyChangeEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController extends AbstractController implements Initializable {
    @FXML private Button newGameBtn;
    @FXML private Button exitGameBtn;

    public MenuController(Model model) {
        super(model);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newGameBtn.disableProperty().bind(getModel().gameRunningProperty());
        exitGameBtn.disableProperty().bind(Bindings.not(getModel().gameRunningProperty()));
    }

    private Model getModel() { return (Model)model; }

    @FXML
    private void newGameAction(ActionEvent e) {
        model.newGame();
    }

    @FXML
    private void exitGameAction(ActionEvent e) {
        model.exitGame();
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
    }

}
