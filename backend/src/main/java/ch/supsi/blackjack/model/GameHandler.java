package ch.supsi.blackjack.model;

public interface GameHandler {

    void newGame();
    void exitGame();
    void hit(int playerID);
    void stand();
    void bet(int betValue);
    void confirmBet();
    void hitTwice(int playerID);
    void tableSetupComplete();
    Coin[] getCoins();

}
