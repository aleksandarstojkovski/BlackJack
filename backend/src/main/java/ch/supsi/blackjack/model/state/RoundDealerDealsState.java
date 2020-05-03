package ch.supsi.blackjack.model.state;

/**
 * This is the turn of the Dealer.
 * He can hit or stand.
 * In case of Bust the Round is over, players won
 * In case of Stand, cards are compared against the players
 * Next State: DealerBustState,UpdateTableState
*/
public class RoundDealerDealsState implements RoundState {

    public RoundDealerDealsState() {printStatus();}

    @Override
    public void next(GameStateManager round) {
        round.setState(new RoundUpdateTableState());
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
        if(round.getDealerHand().isBusted()){
            round.setState(new RoundDealerBustState());
        } else {
            next(round);
        }
        round.goNextState();
    }
}
