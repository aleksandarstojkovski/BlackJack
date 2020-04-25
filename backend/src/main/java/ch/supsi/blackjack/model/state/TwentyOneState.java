package ch.supsi.blackjack.model.state;

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