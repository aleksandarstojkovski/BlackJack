package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.exception.InvalidDecksContainerSizeException;

/**
 * Manage external interaction from controller
 */
public interface GameHandler {

    void newGame(String nickName, int numberOfDecks) throws InvalidDecksContainerSizeException;
    void exitRound();
    void hit();
    void stand();
    void bet(int betValue);
    void confirmBet();
    void nextRound();
    Coin[] getCoins();

}
