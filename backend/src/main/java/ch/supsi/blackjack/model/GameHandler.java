package ch.supsi.blackjack.model;

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
