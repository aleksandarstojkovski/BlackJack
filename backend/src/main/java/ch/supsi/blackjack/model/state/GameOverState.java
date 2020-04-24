package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Model;

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
        model.exit();
    }

}