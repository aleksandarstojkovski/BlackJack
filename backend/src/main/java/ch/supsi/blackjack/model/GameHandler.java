package ch.supsi.blackjack.model;

/**
 * Manage external interaction from controller
 */
public interface GameHandler {

    void newGame();
    void exitGame();
    void hit();
    void stand();
    void bet(int betValue);
    void confirmBet();
    void nextRound();
    Coin[] getCoins();

}
