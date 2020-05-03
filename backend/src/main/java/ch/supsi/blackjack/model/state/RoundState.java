package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Round;

public interface RoundState {
    void next(GameStateManager round);
    void prev(GameStateManager round);
    void printStatus();
    void updateState(GameStateManager round);
}
