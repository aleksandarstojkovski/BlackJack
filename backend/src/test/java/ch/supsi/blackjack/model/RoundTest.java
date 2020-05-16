package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.exception.InvalidDecksContainerSizeException;
import ch.supsi.blackjack.model.state.round.BetState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {
    Round round;
    GameModel model;
    Player player;
    Dealer dealer;

    @BeforeEach
    void setup() throws InvalidDecksContainerSizeException {
        DecksContainer decks = new DecksContainer(DecksContainer.DEFAULT_NUMBER_OF_DECKS);
        dealer = new Dealer(decks);
        player = new Player("Test");
        model = MockGameModel.build();
    }

    @Test
    void setState()  {
        round = new Round(model, player, dealer);
        round.setState(new BetState(round));
        assertNotEquals(new BetState(round), round.getState());
    }

    @Test
    void getGameModel() {
        round = new Round(model, player, dealer);
        assertEquals(model, round.getGameModel());
    }
}