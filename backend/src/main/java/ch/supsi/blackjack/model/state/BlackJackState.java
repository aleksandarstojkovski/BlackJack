package ch.supsi.blackjack.model.state;

public class BlackJackState implements GameState {

    // singleton
    private static final BlackJackState instance = new BlackJackState();

    private BlackJackState() {
    }

    public static BlackJackState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    // business logic and state transition
    @Override
    public void updateState(GameStateManager model) {
        // player made blackjack within first two cards
        model.setPlayerBlackjack();
        model.setCurrentState(UpdateTableState.instance());
        model.goNextState();
    }

}