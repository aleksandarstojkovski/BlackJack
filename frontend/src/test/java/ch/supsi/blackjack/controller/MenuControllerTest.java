package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.model.GameModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MenuControllerTest {

    @Test
    void initialState() {
        GameModel mockGameModel = MockModel.build();
        MenuController controller = new MenuController(mockGameModel);

//        assertFalse(controller.getDisableNewGame());
//        assertTrue(controller.getDisableBet());
//        assertTrue(controller.getDisableHitAndStand());
//        assertTrue(controller.getDisableExitGame());
    }

    @Test
    void startGame() {
        // Mock Model
        GameModel mockGameModel = MockModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockGameModel);
        controller.newGameAction(null);

//        assertTrue(controller.getDisableNewGame());
//        assertTrue(controller.getDisableBet());
//        assertTrue(controller.getDisableHitAndStand());
//        assertFalse(controller.getDisableExitGame());
    }

    @Test
    void gameFinish() {
        // Mock Model
        GameModel mockGameModel = MockModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockGameModel);
        controller.newGameAction(null);
        controller.exitGameAction(null);

//        assertFalse(controller.getDisableNewGame());
//        assertTrue(controller.getDisableExitGame());
//        assertTrue(controller.getDisableHitAndStand());
//        assertTrue(controller.getDisableBet());
//        assertTrue(controller.getDisableNextRound());
    }

    @Test
    void nextRound() {
        // Mock Model
        GameModel mockGameModel = MockModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockGameModel);
        mockGameModel.nextRound();

//        assertTrue(controller.getDisableNextRound());
    }

    @Test
    void newBet() {
        // Mock Model
        GameModel mockGameModel = MockModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockGameModel);
        controller.newGameAction(null);
        mockGameModel.bet(50);

//        assertFalse(controller.getDisableBet());
    }

    @Test
    void confirmBet() {
        // Mock Model
        GameModel mockGameModel = MockModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockGameModel);
        controller.newGameAction(null);
        mockGameModel.bet(50);
        mockGameModel.confirmBet();

//        assertTrue(controller.getDisableBet());
//        assertFalse(controller.getDisableHitAndStand());
    }

    @Test
    void hitAction() {
        // Mock Model
        GameModel mockGameModel = MockModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockGameModel);
        controller.newGameAction(null);
        mockGameModel.bet(50);
        mockGameModel.confirmBet();
        mockGameModel.stand();

//        assertTrue(controller.getDisableHitAndStand());
    }

    @Test
    void standAction() {
        // Mock Model
        GameModel mockGameModel = MockModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockGameModel);
        controller.newGameAction(null);
        controller.standAction(null);

//        assertTrue(controller.getDisableHitAndStand());
    }

}