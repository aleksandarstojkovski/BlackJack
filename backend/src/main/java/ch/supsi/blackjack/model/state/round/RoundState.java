package ch.supsi.blackjack.model.state.round;

import ch.supsi.blackjack.model.RoundHandler;

public interface RoundState {
    void next();
    void prev();
    void printStatus();
    void updateState();

}
