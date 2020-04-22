package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.event.*;
import ch.supsi.blackjack.model.Model;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.beans.PropertyChangeEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController extends AbstractController implements Initializable {
    public MenuController(Model model) {
        super(model);
    }

    private final BooleanProperty disableNextRound = new SimpleBooleanProperty(true);
    // required for FXML binding
    public BooleanProperty disableNextRoundProperty() {
        return disableNextRound;
    }
    public boolean getDisableNextRound()
    {
        return disableNextRound.get();
    }

    private final BooleanProperty disableHitAndStand = new SimpleBooleanProperty(true);
    // required for FXML binding
    public BooleanProperty disableHitAndStandProperty() {
        return disableHitAndStand;
    }
    public boolean getDisableHitAndStand()
    {
        return disableHitAndStand.get();
    }

    private final BooleanProperty disableBet = new SimpleBooleanProperty(true);
    // required for FXML binding
    public BooleanProperty disableBetProperty() {
        return disableBet;
    }
    public boolean getDisableBet()
    {
        return disableBet.get();
    }

    private final BooleanProperty disableExitGame = new SimpleBooleanProperty(false);
    // required for FXML binding
    public BooleanProperty disableExitGameProperty() {
        return disableExitGame;
    }
    public boolean getDisableExitGame()
    {
        return disableExitGame.get();
    }

    private final BooleanProperty disableNewGame = new SimpleBooleanProperty(false);
    // required for FXML binding
    public BooleanProperty disableNewGameProperty() {
        return disableNewGame;
    }
    public boolean getDisableNewGame()
    {
        return disableNewGame.get();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        BooleanBinding disableHitAndStand = getModel().gameRunningProperty().not().or(
//                getModel().dealsOpenProperty().not().or(
//                        getModel().playerBustedProperty().or(
//                                getModel().playerStandProperty()
//                        )
//                )
//        );
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event instanceof GameStartedEvent) {
            disableNewGame.set(true);
            disableExitGame.set(false);
            disableHitAndStand.set(true);
            disableBet.set(true);
        } else if (event instanceof GameFinishedEvent) {
            disableNewGame.set(false);
            disableExitGame.set(true);
            disableHitAndStand.set(true);
            disableBet.set(true);
            disableNextRound.set(true);
        } else if (event instanceof NewRoundEvent) {
            disableNextRound.set(true);
        } else if (event instanceof NewBetEvent) {
            disableBet.set(false);
        } else if (event instanceof BetConfirmedEvent) {
            disableBet.set(true);
            disableHitAndStand.set(false);
        } else if (event instanceof StandEvent) {
            disableHitAndStand.set(true);
        } else if (event instanceof PlayerBustedEvent) {
            disableHitAndStand.set(true);
        } else if (event instanceof RoundCompletedEvent) {
            disableNextRound.set(false);
            disableHitAndStand.set(true);
        } else if (event instanceof PlayerBlackjackEvent) {
            disableHitAndStand.set(true);
            disableBet.set(true);
            disableNextRound.set(true);
        }
    }

    @FXML
    void newGameAction(ActionEvent actionEvent) {
        model.newGame();
    }

    @FXML
    void exitGameAction(ActionEvent actionEvent) {
        model.exitGame();
    }

    @FXML
    void hitAction(ActionEvent actionEvent) {
        model.hit();
    }

    @FXML
    void standAction(ActionEvent actionEvent) {
        model.stand();
    }

    @FXML
    public void betAction(ActionEvent actionEvent) {model.confirmBet(); }

    public void nextRoundAction(ActionEvent actionEvent) {
        model.nextRound();
    }

}
