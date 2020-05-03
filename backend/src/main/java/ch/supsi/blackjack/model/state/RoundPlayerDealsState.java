package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Hand;

/**
 * This is the turn of the Main Player.
 * He can hit or stand.
 * In case of Blackjack the Round is over, he won
 * In case of Bust the Round is over, dealer won
 */
public class RoundPlayerDealsState implements RoundState {

    public RoundPlayerDealsState() {
        printStatus();
    }

    @Override
    public void next(GameStateManager round) {
        round.setState(new RoundDealerDealsState());
    }

    @Override
    public void prev(GameStateManager round) {

    }

    @Override
    public void printStatus() {
        System.out.println("Current state: " + this.getClass().toString());
    }

    // business logic and state transition
    @Override
    public void updateState(GameStateManager round) {
        if (round.isPlayerStand()){
            //Order of call is important
            next(round);
            round.updateDealer();
            round.computeDealer();
        } else {
            Hand hand = round.getPlayerHand();
            if (hand.isBusted()){
                round.setState(new RoundPlayerBustState());
                round.updateDealer();
                round.goNextState();
            } else if (hand.isBlackJack()) {
                round.setState(new RoundTwentyOneState());
                round.updateDealer();
                round.goNextState();
            } else {
                // stay in this state until the user bust or stands or makes blackjack
                round.setState(new RoundPlayerDealsState());
            }
        }
    }
}