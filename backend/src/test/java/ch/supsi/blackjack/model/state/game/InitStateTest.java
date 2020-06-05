package ch.supsi.blackjack.model.state.game;

import ch.supsi.blackjack.model.GameHandler;
import ch.supsi.blackjack.model.GameModel;
import ch.supsi.blackjack.model.MockGameModel;
import ch.supsi.blackjack.model.RoundHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class InitStateTest {
    InitState state;
    GameModel model;

    @BeforeEach
    void setup() {
        // mock GameModel
        model = Mockito.mock(GameModel.class);
        // mock RoundHandler
        Mockito.doAnswer((Answer<RoundHandler>) invocation -> Mockito.mock(RoundHandler.class))
                .when(model)
                .getRound();

        state = new InitState(model);
    }

    @Test
    void startGame() {
        state.startGame();
        verify(model, times(1)).setGameState(model.getRoundState());
    }

    @Test
    void exitGame() {
        state.exitGame();
        verify(model, times(0)).setGameState(any());
    }
}