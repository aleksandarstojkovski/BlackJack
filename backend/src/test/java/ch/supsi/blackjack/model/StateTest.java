package ch.supsi.blackjack.model;
import ch.supsi.blackjack.model.state.game.InitState;
import ch.supsi.blackjack.model.state.game.RoundState;
import ch.supsi.blackjack.model.state.round.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StateTest {

    private GameModel gameModel;
    private RoundHandler roundMediator;

    @BeforeAll
    void setup(){
        gameModel = GameModel.instance();
        roundMediator = new RoundMediator(gameModel,"player","dealer");
    }

    @Test
    void testInitState(){
        InitState initState = new InitState(GameModel.instance());
        assertNotNull(initState);
    }

    @Test
    void testRoundState(){
        RoundState roundState = new RoundState(GameModel.instance());
        assertNotNull(roundState);
    }

    @Test
    void testBetState(){
        BetState state = new BetState(roundMediator);
        assertNotNull(state);
    }

    @Test
    void testBlackJackState(){
        BlackJackState state = new BlackJackState(roundMediator);
        assertNotNull(state);
    }

    @Test
    void testDealerBustState(){
        DealerBustState state = new DealerBustState(roundMediator);
        assertNotNull(state);
    }

    @Test
    void testDealerDealsState(){
        DealerDealsState state = new DealerDealsState(roundMediator);
        assertNotNull(state);
    }

    @Test
    void testGameOverState(){
        GameOverState state = new GameOverState(roundMediator);
        assertNotNull(state);
    }

    @Test
    void testPlayerBustState(){
        PlayerBustState state = new PlayerBustState(roundMediator);
        assertNotNull(state);
    }

    @Test
    void testPlayerDealsState(){
        PlayerDealsState state = new PlayerDealsState(roundMediator);
        assertNotNull(state);
    }

    @Test
    void testSetupTableState(){
        SetupTableState state = new SetupTableState(roundMediator);
        assertNotNull(state);
    }

    @Test
    void testTwentyOneState(){
        TwentyOneState state = new TwentyOneState(roundMediator);
        assertNotNull(state);
    }

    @Test
    void testUpdateTableState(){
        UpdateTableState state = new UpdateTableState(roundMediator);
        assertNotNull(state);
    }

}
