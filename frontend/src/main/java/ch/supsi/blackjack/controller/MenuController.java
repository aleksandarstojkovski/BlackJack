package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.event.*;
import ch.supsi.blackjack.model.Model;
import ch.supsi.blackjack.model.state.BetState;
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
    public BooleanProperty disableNextRoundProperty() {
        return disableNextRound;
    } // required for FXML binding
    public boolean getDisableNextRound()
    {
        return disableNextRound.get();
    }

    private final BooleanProperty disableHitAndStand = new SimpleBooleanProperty(true);
    public BooleanProperty disableHitAndStandProperty() {
        return disableHitAndStand;
    } // required for FXML binding
    public boolean getDisableHitAndStand()
    {
        return disableHitAndStand.get();
    }

    private final BooleanProperty disableBet = new SimpleBooleanProperty(true);
    public BooleanProperty disableBetProperty() {
        return disableBet;
    } // required for FXML binding
    public boolean getDisableBet()
    {
        return disableBet.get();
    }

    private final BooleanProperty disableExitGame = new SimpleBooleanProperty(false);
    public BooleanProperty disableExitGameProperty() {
        return disableExitGame;
    } // required for FXML binding
    public boolean getDisableExitGame()
    {
        return disableExitGame.get();
    }

    private final BooleanProperty disableNewGame = new SimpleBooleanProperty(false);
    public BooleanProperty disableNewGameProperty() {
        return disableNewGame;
    } // required for FXML binding
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

    private Model getModel() { return (Model)model; }

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
        }
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
