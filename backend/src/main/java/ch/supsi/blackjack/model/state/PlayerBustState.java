package ch.supsi.blackjack.model.state;

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