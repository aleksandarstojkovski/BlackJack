package ch.supsi.blackjack.model.state;

/**
 * This is the initial phase of a Round.
 * The player has to bet.
 * Once confirmed the cards can be distributed
 * Next State: BetState, SetupTableState
 */
public class BetState implements GameState {

    // singleton
    private static final BetState instance = new BetState();

    private BetState() {
    }

    public static BetState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    // business logic and state transition
    @Override
    public void updateState(GameStateManager model) {
        // bet confirmed
        if (model.isBetConfirmed()){
            model.openRound();
            model.setCurrentState(SetupTableState.instance());
        }
    }

}