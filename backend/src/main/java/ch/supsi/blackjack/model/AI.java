package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.state.GameStateManager;

public interface AI {
    void compute(GameStateManager model);
}
