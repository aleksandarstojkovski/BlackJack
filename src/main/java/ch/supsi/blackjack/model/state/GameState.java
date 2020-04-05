package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Model;

public interface GameState {
    public void updateState(Model model);
}
