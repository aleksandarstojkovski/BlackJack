package ch.supsi.blackjack.model;

public interface GameHandler {
    void newGame();
    void exitGame();
    void getCard();
    void stopCard();
}
