package ch.supsi.blackjack.model.state;

public class InitState implements GameState {

    // singleton
    private static final InitState instance = new InitState();

    private InitState() {
    }

    public static InitState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    // business logic and state transition
    @Override
    public void updateState(GameStateManager model) {
        model.setCurrentState(BetState.instance());
    }

}