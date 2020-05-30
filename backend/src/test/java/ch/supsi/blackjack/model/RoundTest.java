package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.state.round.BetState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {

    Round round;
    GameModel model;
    Player player;
    Dealer dealer;
    DecksContainer decks;

    @BeforeEach
    void setup()  {
        DecksContainer decks = new DecksContainer.Builder().build();
        dealer = new Dealer("dealer",1000);
        player = new Player("player",1000);
        model = MockGameModel.build();
    }

    @Test
    void setState()  {
        round = new Round(model,player,dealer, decks);
        round.setState(new BetState(round));
        assertNotEquals(new BetState(round), round.getState());
    }

    @Test
    void getGameModel() {
        round = new Round(model,player,dealer, decks);
        assertEquals(model, round.getGameModel());
    }

}