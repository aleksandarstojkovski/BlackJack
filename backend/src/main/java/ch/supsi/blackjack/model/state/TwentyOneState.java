package ch.supsi.blackjack.model.state;

/**
 * Player asked for new cards (hit) and reached 21
 * It's not enough to win automatically
 * We need to compare with Dealer cards
 * Next State: UpdateTableState
 */
public class TwentyOneState implements GameState {

    // singleton
    private static final TwentyOneState instance = new TwentyOneState();

    private TwentyOneState() {
    }

    public static TwentyOneState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    // business logic and state transition
    @Override
    public void updateState(GameStateManager model) {
        model.setPlayerTwentyOne();
        model.setCurrentState(UpdateTableState.instance());
        model.goNextState();
    }

}