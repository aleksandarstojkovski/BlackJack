package ch.supsi.blackjack.model.state;

/**
 * Player confirmed his bet and cards was distributed
 * If player got 21 with 2 cards the Round is over, main player won.
 * Next State: BlackJackState,PlayerDealsState
 */
public class RoundSetupTableState implements RoundState {

    public RoundSetupTableState(){
        printStatus();
    }

    @Override
    public void next(GameStateManager round) {
        round.setState(new RoundPlayerDealsState());
    }

    @Override
    public void prev(GameStateManager round) {
        round.setState(new RoundBetState());
    }

    @Override
    public void printStatus() {
        System.out.println("Current state: " + this.getClass().toString());
    }

    // business logic and state transition
    @Override
    public void updateState(GameStateManager round) {
        if (round.getPlayerHand().isBlackJack()){
            round.setState(new RoundBlackJackState());
            round.goNextState();
        } else {
            next(round);
        }
    }
}
