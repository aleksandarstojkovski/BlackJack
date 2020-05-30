package ch.supsi.blackjack.model.state.round;

import ch.supsi.blackjack.model.Hand;
import ch.supsi.blackjack.model.RoundHandler;

/**
 * This is the turn of the Main Player.
 * He can hit or stand.
 * In case of Blackjack the Round is over, he won
 * In case of Bust the Round is over, dealer won
 */
public class PlayerDealsState implements RoundState {

    private final RoundHandler round;

    public PlayerDealsState(RoundHandler round) {
        this.round = round;
        printStatus();
    }

    @Override
    public void next() {
        round.setState(new DealerDealsState(round));
    }

    @Override
    public void prev() {

    }

    @Override
    public void printStatus() {
        System.out.println("Current state: " + this.getClass().toString());
    }

    // business logic and state transition
    @Override
    public void updateState() {
        if (round.isPlayerStand()){
            //Order of call is important
            next();
            round.computeDealer();
            round.updateDealer();
        } else {
            Hand hand = round.getPlayerHand();
            if (hand.isBusted()){
                round.setState(new PlayerBustState(round));
                round.updateDealer();
                round.goNextState();
            } else if (hand.isBlackJack()) {
                round.setState(new TwentyOneState(round));
                round.updateDealer();
                round.goNextState();
            } else {
                // stay in this state until the user bust or stands or makes blackjack
                round.setState(new PlayerDealsState(round));
            }
        }
    }
}