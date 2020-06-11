package ch.supsi.blackjack.model.state.round;

import ch.supsi.blackjack.model.RoundHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PlayerDealsStateTest {
    private RoundHandler round;
    private RoundState state;

    @BeforeEach
    void setup() {
        round = Mockito.mock(RoundHandler.class);
        state = new PlayerDealsState(round);
    }

    @Test
    void updateState() {
        state.updateState();
        verify(round, times(1)).isPlayerStand();
    }

    @Test
    void next() {
        state.next();
        verify(round, times(1)).setState(Mockito.any());
    }

    @Test
    void prev() {
        state.prev();
        verify(round, times(0)).setState(Mockito.any());
    }

    @Test
    void printStatus() {
        state.printStatus();
        verify(round, times(0)).setState(Mockito.any());
    }
}