package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.event.ExitGameEvent;
import ch.supsi.blackjack.event.NewGameEvent;
import ch.supsi.blackjack.model.Model;
import javafx.beans.binding.BooleanBinding;
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
    @FXML private Button hitBtn;
    @FXML private Button standBtn;

    public MenuController(Model model) {
        super(model);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // using events instead of property binding
        BooleanBinding disableHitAndStand = getModel().gameRunningProperty().not().or(
                getModel().dealsOpenProperty().not().or(
                        getModel().playerBurstProperty().or(
                                getModel().playerStandProperty()
                        )
                )
        );
        BooleanBinding disableBetBtn = getModel().gameRunningProperty().not().or(
                getModel().betsOpenProperty().not()
        );
        newGameBtn.disableProperty().bind(getModel().gameRunningProperty());
        exitGameBtn.disableProperty().bind(getModel().gameRunningProperty().not());
        hitBtn.disableProperty().bind(disableHitAndStand);
        standBtn.disableProperty().bind(disableHitAndStand);
        betBtn.disableProperty().bind(disableBetBtn);
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
    void hitAction(ActionEvent actionEvent) {
        model.playerHit();
    }

    @FXML
    void standAction(ActionEvent actionEvent) {
        model.stand();
    }

    @FXML
    public void betAction(ActionEvent actionEvent) {model.confirmBet(); }

}
