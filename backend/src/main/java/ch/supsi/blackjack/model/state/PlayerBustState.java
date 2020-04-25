package ch.supsi.blackjack.model.state;

/**
 * Player decided to hit, but he exceeds the limit of 21.
 * The Round is over, main player won.
 * Next State: UpdateTableState
 */
public class PlayerBustState implements GameState {
    // singleton
    private static final PlayerBustState instance = new PlayerBustState();

    private PlayerBustState() {
    }

    public static PlayerBustState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    // business logic and state transition
    @Override
    public void updateState(GameStateManager model) {
        model.setPlayerBusted();
        model.setCurrentState(UpdateTableState.instance());
        model.goNextState();
    }

}