package ch.supsi.blackjack.model.state;

/**
 * Player confirmed his bet and cards was distributed
 * If player got 21 with 2 cards the Round is over, main player won.
 * Next State: BlackJackState,PlayerDealsState
 */
public class SetupTableState implements GameState {
    // singleton
    private static final SetupTableState instance = new SetupTableState();

    private SetupTableState() {
    }

    public static SetupTableState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    // business logic and state transition
    @Override
    public void updateState(GameStateManager model) {
        if (model.getPlayerHand().isBlackJack()){
            model.setCurrentState(BlackJackState.instance());
            model.goNextState();
        } else {
            model.setCurrentState(PlayerDealsState.instance());
        }
    }
}
