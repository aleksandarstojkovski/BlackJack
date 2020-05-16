package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.model.DecksContainer;
import ch.supsi.blackjack.model.GameHandler;
import ch.supsi.blackjack.model.exception.InvalidDecksContainerSizeException;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuController extends AbstractController  {
    public MenuController(GameHandler model) {
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

    private final BooleanProperty disableExitGame = new SimpleBooleanProperty(true);
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


    @FXML
    void newGameAction(ActionEvent actionEvent) {
        //TODO: ask nickname to user
        String nickName = "Player 1";
        try {
            model.newGame(nickName, DecksContainer.DEFAULT_NUMBER_OF_DECKS);
        } catch (InvalidDecksContainerSizeException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void exitGameAction(ActionEvent actionEvent) {
        model.exitRound();
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

    @FXML
    public void nextRoundAction(ActionEvent actionEvent) {
        model.nextRound();
    }

    public void onGameStarted() {
        disableNewGame.set(true);
        disableExitGame.set(false);
        disableHitAndStand.set(true);
        disableBet.set(true);
    }

    public void onGameFinished() {
        disableNewGame.set(false);
        disableExitGame.set(true);
        disableHitAndStand.set(true);
        disableBet.set(true);
        disableNextRound.set(true);
    }

    public void onNewRound() {
        disableNextRound.set(true);
    }

    public void onNewBet() {
        disableBet.set(false);
    }

    public void onBetConfirmed() {
        disableBet.set(true);
        disableHitAndStand.set(false);
    }

    public void onStand() {
        disableHitAndStand.set(true);
    }

    public void onPlayerBusted() {
        disableHitAndStand.set(true);
    }

    public void onRoundCompleted() {
        disableNextRound.set(false);
        disableHitAndStand.set(true);
    }

    public void onPlayerBlackjack() {
        disableHitAndStand.set(true);
        disableBet.set(true);
        disableNextRound.set(true);
    }
}
