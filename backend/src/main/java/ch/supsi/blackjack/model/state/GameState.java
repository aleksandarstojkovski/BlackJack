package ch.supsi.blackjack.model.state;

public interface GameState {
    void updateState(GameStateManager round);
}
