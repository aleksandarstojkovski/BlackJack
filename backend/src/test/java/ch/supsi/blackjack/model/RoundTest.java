package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.state.round.BetState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {

    RoundMediator round;
    GameModel model;

    @BeforeEach
    void setup()  {
        model = MockGameModel.build();
    }

    @Test
    void setState()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new BetState(round));
        assertNotEquals(new BetState(round), round.getState());
    }

    @Test
    void getGameModel() {
        round = new RoundMediator(model,"Player1","Dealer1");
        assertEquals(model, round.getGameModel());
    }

}