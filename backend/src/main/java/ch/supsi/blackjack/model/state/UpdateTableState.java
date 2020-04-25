package ch.supsi.blackjack.model.state;

/**
 * The Round is completed
 * If player has more money he can still play
 * Otherwise the game is over
 * Next State: BetState,GameOverState
 */
public class UpdateTableState implements GameState {

    // singleton
    private static final UpdateTableState instance = new UpdateTableState();

    private UpdateTableState() {
    }

    public static UpdateTableState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    // business logic and state transition
    @Override
    public void updateState(GameStateManager model) {
        model.setRoundCompleted();

        if (model.isPlayerWithMoney()){
            model.setCurrentState(BetState.instance());
        } else {
            model.setGameOver();
            model.setCurrentState(GameOverState.instance());
            model.goNextState();
        }

    }

}