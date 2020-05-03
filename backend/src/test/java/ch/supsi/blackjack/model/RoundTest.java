package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.state.GameStateManager;
import ch.supsi.blackjack.model.state.RoundInitState;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeSupport;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {
    GameStateManager round;

    @Test
    void setState() {
        round = new Round(new PropertyChangeSupport(this),new Player("Test"),new Dealer());
        round.setState(new RoundInitState());
        assertNotEquals(new RoundInitState(),round.getState());
    }
}