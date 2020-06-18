package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.state.game.InitState;
import ch.supsi.blackjack.model.state.game.RoundState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


class GameModelTest {

    private GameModel testGameModel;
    private InitState mockInitState;
    private  RoundState mockRoundState;
    private RoundHandler mockRoundHandler;

    @BeforeEach void setup() {
        mockInitState = mock(InitState.class);
        mockRoundState = mock(RoundState.class);
        mockRoundHandler = mock(RoundHandler.class);
        testGameModel = new GameModel(mockInitState,mockRoundState,mockRoundHandler);
    }

    @Test
    void getRound() {
        testGameModel.newGame();
        assertNotNull(testGameModel.getRound());
    }

    @Test
    void setGameState() {
        testGameModel.setGameState(mockRoundState);
        assertTrue(testGameModel.gameState instanceof RoundState);
    }

    @Test
    void startGame() {
        testGameModel.newGame();
        verify(mockInitState, times(1)).startGame();
    }

    @Test
    void exitGame() {
        testGameModel.newGame();
        testGameModel.setGameState(mockRoundState);
        testGameModel.exitGame();
        verify(mockRoundState, times(1)).exitGame();
    }

    @Test
    void getInitState() {
        assertTrue(testGameModel.getInitState() instanceof InitState);
    }

    @Test
    void getRoundState() {
        testGameModel.setGameState(new RoundState(testGameModel));
        assertTrue(testGameModel.getRoundState() instanceof RoundState);
    }


    @Test
    void newGame() {
        testGameModel.newGame();
        assertNotNull(testGameModel.getRound());
    }

    @Test
    void exitRound() {
        testGameModel.exitRound();
        verify(mockRoundHandler,times(1)).exitRound();
    }

    @Test
    void nextRound() {
        testGameModel.nextRound();
        verify(mockRoundHandler,times(1)).nextRound();
    }

    @Test
    void hit() {
        testGameModel.hit();
        verify(mockRoundHandler,times(1)).playerHit();
    }

    @Test
    void stand() {
        testGameModel.stand();
        verify(mockRoundHandler,times(1)).setPlayerStand();
    }

    @Test
    void bet() {
        testGameModel.bet(100);
        verify(mockRoundHandler,times(1)).playerBet(100);
    }

    @Test
    void confirmBet() {
        testGameModel.confirmBet();
        verify(mockRoundHandler,times(1)).playerConfirmBet();
    }
}