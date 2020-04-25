package ch.supsi.blackjack.model.state;

/**
 * This is the end of all Rounds
 * Players don't have any more money or simply don't wont to play more
 * Next State: InitState
 */
public class GameOverState implements GameState {

    // singleton
    private static final GameOverState instance = new GameOverState();

    private GameOverState() {
    }

    public static GameOverState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    // business logic and state transition
    @Override
    public void updateState(GameStateManager model) {
        model.setCurrentState(InitState.instance());
        model.exitGame();
    }

}