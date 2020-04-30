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
        controller.newGameAction(null);
        controller.exitGameAction(null);

        assertFalse(controller.getDisableNewGame());
        assertTrue(controller.getDisableExitGame());
        assertTrue(controller.getDisableHitAndStand());
        assertTrue(controller.getDisableBet());
        assertTrue(controller.getDisableNextRound());
    }
/*

    @Test
    void hitAction() {
        // Mock Model
        Model mockModel = MockModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockModel);
        controller.newGameAction(null);
        controller.hitAction(null);

        assertFalse(controller.getDisableNewGame());
        assertTrue(controller.getDisableExitGame());
        assertTrue(controller.getDisableHitAndStand());
        assertTrue(controller.getDisableBet());
        assertTrue(controller.getDisableNextRound());
    }

    @Test
    void standAction() {
        // Mock Model
        Model mockModel = MockModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockModel);
        controller.newGameAction(null);
        controller.standAction(null);

        assertTrue(controller.getDisableHitAndStand());
    }

    @Test
    void betAction() {
        // Mock Model
        Model mockModel = MockModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockModel);
        controller.betAction(null);


        assertTrue(controller.getDisableBet());
        assertFalse(controller.getDisableHitAndStand());
    }

    @Test
    void nextRound() {
        // Mock Model
        Model mockModel = MockModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockModel);
        controller.nextRoundAction(null);

        assertTrue(controller.getDisableNextRound());
    }
*/

}