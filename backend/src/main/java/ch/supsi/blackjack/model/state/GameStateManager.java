package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Player;

public interface GameStateManager {
    void setCurrentState(GameState state);
    void goNextState();

    boolean isBetConfirmed();
    void openRound();

    void setDealerBusted();
    void setPlayerBlackjack();
    void setPlayerBusted();
    void hit(Player aiEntity);

    void start();
    void exit();
    void next();
    void playerHit();
    void playerStand();
    void playerBet(int amount);
    void playerConfirmBet();

    int getDealerHandValue();
    int getPlayerHandValue();

    boolean isPlayerStand();

    void updateDealer();

    void computeDealer();

    void setRoundCompleted();

    boolean isPlayerWithMoney();

    void setGameOver();

    void setPlayerTwentyone();
}
