package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.state.round.BetState;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeSupport;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {
    Round round;

    @Test
    void setState() {
        round = new Round(GameModel.instance(),new Player("Test"),new Dealer());
        round.setState(new BetState(round));
        assertNotEquals(new BetState(round),round.getState());
    }

    @Test
    void getGameModel() {
        GameModel tempGameModel = GameModel.instance();
        round = new Round(tempGameModel,new Player("Test"),new Dealer());
        assertEquals(tempGameModel,round.getGameModel());
    }
}