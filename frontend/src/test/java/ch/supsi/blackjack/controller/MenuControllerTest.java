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
        mockModel.addPropertyChangeListener(controller);

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
        mockModel.addPropertyChangeListener(controller);
        controller.newGameAction(null);

        assertTrue(controller.getDisableNewGame());
        assertTrue(controller.getDisableBet());
        assertTrue(controller.getDisableHitAndStand());
        assertFalse(controller.getDisableExitGame());
    }
}