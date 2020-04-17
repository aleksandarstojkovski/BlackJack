package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Model;

public interface GameState {
    void updateState(Model model);
}
