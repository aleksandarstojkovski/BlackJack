package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.event.ExitGameEvent;
import ch.supsi.blackjack.event.NewGameEvent;
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

    @FXML private Button betBtn;
    @FXML private Button newGameBtn;
    @FXML private Button exitGameBtn;
    @FXML private Button getCardBtn;
    @FXML private Button stopCardBtn;

    public MenuController(Model model) {
        super(model);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // using events instead of property binding
        newGameBtn.disableProperty().bind(getModel().gameRunningProperty());
        exitGameBtn.disableProperty().bind(Bindings.not(getModel().gameRunningProperty()));
        getCardBtn.disableProperty().bind(
                Bindings.or(
                        getModel().gameRunningProperty().not(),
                        getModel().dealsOpenProperty().not()
                )
        );
        stopCardBtn.disableProperty().bind(
                Bindings.or(
                        getModel().gameRunningProperty().not(),
                        getModel().dealsOpenProperty().not()
                )
        );
        betBtn.disableProperty().bind(
                Bindings.or(
                        getModel().gameRunningProperty().not(),
                        getModel().betsOpenProperty().not()
                )
        );
    }

    private Model getModel() { return (Model)model; }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event instanceof NewGameEvent) {
            handleNewGame((NewGameEvent) event);
        }
        if (event instanceof ExitGameEvent) {
            handleExitGame((ExitGameEvent) event);
        }
    }

    private void handleExitGame(ExitGameEvent event) {
    }

    private void handleNewGame(NewGameEvent event) {

    }

    @FXML
    void newGameAction(ActionEvent e) {
        model.newGame();
    }

    @FXML
    void exitGameAction(ActionEvent e) {
        model.exitGame();
    }

    @FXML
    void getCardAction(ActionEvent actionEvent) {
        model.getCard();
    }

    @FXML
    void stopCardAction(ActionEvent actionEvent) {
        model.stopCard();
    }

    @FXML
    public void betAction(ActionEvent actionEvent) {model.confirmBet(); }

}
