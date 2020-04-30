package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.model.Model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MenuControllerTest {

    @Test
    void initialState() {
        Model mockModel = MockModel.build();
        MenuController controller = new MenuController(mockModel);

        assertFalse(controller.getDisableNewGame());
        assertTrue(controller.getDisableBet());
        assertTrue(controller.getDisableHitAndStand());
        assertTrue(controller.getDisableExitGame());
    }

    @Test
    void startGame() {
        // Mock Model
        Model mockModel = MockModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockModel);
        controller.newGameAction(null);

        assertTrue(controller.getDisableNewGame());
        assertTrue(controller.getDisableBet());
        assertTrue(controller.getDisableHitAndStand());
        assertFalse(controller.getDisableExitGame());
    }

    @Test
    void gameFinish() {
        // Mock Model
        Model mockModel = MockModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockModel);
        controller.exitGameAction(null);

        assertFalse(controller.getDisableNewGame());
        assertTrue(controller.getDisableExitGame());
        assertTrue(controller.getDisableHitAndStand());
        assertTrue(controller.getDisableBet());
        assertTrue(controller.getDisableNextRound());
    }

    @Test
    void newBet() {
        // Mock Model
        Model mockModel = MockModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockModel);
        controller.hitAction(null);

        assertFalse(controller.getDisableNewGame());
        assertTrue(controller.getDisableExitGame());
        assertTrue(controller.getDisableHitAndStand());
        assertTrue(controller.getDisableBet());
        assertTrue(controller.getDisableNextRound());
    }

}