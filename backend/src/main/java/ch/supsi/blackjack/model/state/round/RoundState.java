package ch.supsi.blackjack.model.state.round;

public interface RoundState {
    void next();
    void prev();
    void printStatus();
    void updateState();
}
