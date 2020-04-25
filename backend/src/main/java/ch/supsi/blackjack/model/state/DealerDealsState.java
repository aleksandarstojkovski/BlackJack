package ch.supsi.blackjack.model.state;

/**
 * This is the turn of the Dealer.
 * He can hit or stand.
 * In case of Bust the Round is over, players won
 * In case of Stand, cards are compared against the players
 * Next State: DealerBustState,UpdateTableState
*/
public class DealerDealsState implements GameState {

    // singleton
    private static final DealerDealsState instance = new DealerDealsState();

    private DealerDealsState() {}

    public static DealerDealsState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    // business logic and state transition
    @Override
    public void updateState(GameStateManager model) {
        if(model.getDealerHandValue() > model.BLACKJACK){
            model.setCurrentState(DealerBustState.instance());
        } else {
            model.setCurrentState(UpdateTableState.instance());
        }
        model.goNextState();
    }
}
