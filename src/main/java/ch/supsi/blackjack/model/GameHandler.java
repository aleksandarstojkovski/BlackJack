package ch.supsi.blackjack.model;

public interface GameHandler {

    void newGame();
    void exitGame();
    void getCard();
    void stopCard();
    void bet(int betValue);
    void confirmBet();
    void getCards(int quanitty);
    void tableSetupComplete();
    Coin[] getCoins();

}
