package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Player;

public interface GameStateManager {
    int BLACKJACK = 21;

    void setCurrentState(GameState state);
    void goNextState();

    void startGame();
    void exitGame();
    void nextRound();

    void playerBet(int amount);
    void playerConfirmBet();
    boolean isBetConfirmed();
    void openRound();
    void playerHit();
    void hit(Player player);
    void setPlayerStand();

    void setPlayerTwentyOne();
    void setDealerBusted();
    void setPlayerBlackjack();
    void setPlayerBusted();

    int getDealerHandValue();
    int getPlayerHandValue();

    boolean isPlayerStand();
    boolean isPlayerWithMoney();

    void updateDealer();
    void computeDealer();
    void setRoundCompleted();
    void setGameOver();
}
