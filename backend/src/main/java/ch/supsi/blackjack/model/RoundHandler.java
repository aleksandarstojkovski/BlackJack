package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.Hand;
import ch.supsi.blackjack.model.Player;
import ch.supsi.blackjack.model.state.round.RoundState;

public interface RoundHandler {

    void setState(RoundState state);
    RoundState getState();

    void goNextState();

    void startRound();
    void exitRound();
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

    Hand getHand(Player player);
    Hand getPlayerHand();
    Hand getDealerHand();

    boolean isPlayerStand();
    boolean isPlayerWithMoney();

    void updateDealer();
    void computeDealer();
    void setRoundCompleted();
    void setGameOver();

}
