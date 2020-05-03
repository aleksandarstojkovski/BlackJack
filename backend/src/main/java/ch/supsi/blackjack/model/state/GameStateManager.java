package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Hand;
import ch.supsi.blackjack.model.Player;

public interface GameStateManager {
    void setState(RoundState state);
    RoundState getState();
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

    Hand getDealerHand();
    Hand getPlayerHand();

    boolean isPlayerStand();
    boolean isPlayerWithMoney();

    void updateDealer();
    void computeDealer();
    void setRoundCompleted();
    void setGameOver();
}
