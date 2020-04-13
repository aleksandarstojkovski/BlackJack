package ch.supsi.blackjack.model;

import javafx.stage.Stage;

public interface GameHandler {

    void newGame();
    void exitGame();
    void hit();
    void stand();
    void bet(int betValue);
    void confirmBet();
    void hitTwice(Player player);
    void nextRound();
    Stage getPrimaryStage();
    public void nextState();
    Coin[] getCoins();

}
