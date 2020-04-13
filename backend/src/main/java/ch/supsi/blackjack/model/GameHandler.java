package ch.supsi.blackjack.model;

public interface GameHandler {

    void newGame();
    void exitGame();
    void playerHit();
    void stand();
    void bet(int betValue);
    void confirmBet();
    void hitTwice(Player player);
    void tableSetupComplete();
    Coin[] getCoins();

}
