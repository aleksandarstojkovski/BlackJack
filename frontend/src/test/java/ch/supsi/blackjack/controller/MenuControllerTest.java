package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.model.Model;
import org.junit.Test;

class MenuControllerTest {

    @Test
    void initialState() {
        Model mockModel = MockModel.build();
        MenuController controller = new MenuController(mockModel);

//        assertFalse(controller.getDisableNewGame());
//        assertTrue(controller.getDisableBet());
//        assertTrue(controller.getDisableHitAndStand());
//        assertTrue(controller.getDisableExitGame());
    }

    @Test
    void startGame() {
        // Mock Model
        Model mockModel = MockModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockModel);
        controller.newGameAction(null);

//        assertTrue(controller.getDisableNewGame());
//        assertTrue(controller.getDisableBet());
//        assertTrue(controller.getDisableHitAndStand());
//        assertFalse(controller.getDisableExitGame());
    }

    @Test
    void gameFinish() {
        // Mock Model
        Model mockModel = MockModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockModel);
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
        Model mockModel = MockModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockModel);
        mockModel.nextRound();

//        assertTrue(controller.getDisableNextRound());
    }

    @Test
    void newBet() {
        // Mock Model
        Model mockModel = MockModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockModel);
        controller.newGameAction(null);
        mockModel.bet(50);

//        assertFalse(controller.getDisableBet());
    }

    @Test
    void confirmBet() {
        // Mock Model
        Model mockModel = MockModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockModel);
        controller.newGameAction(null);
        mockModel.bet(50);
        mockModel.confirmBet();

//        assertTrue(controller.getDisableBet());
//        assertFalse(controller.getDisableHitAndStand());
    }

    @Test
    void hitAction() {
        // Mock Model
        Model mockModel = MockModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockModel);
        controller.newGameAction(null);
        mockModel.bet(50);
        mockModel.confirmBet();
        mockModel.stand();

//        assertTrue(controller.getDisableHitAndStand());
    }

    @Test
    void standAction() {
        // Mock Model
        Model mockModel = MockModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockModel);
        controller.newGameAction(null);
        controller.standAction(null);

//        assertTrue(controller.getDisableHitAndStand());
    }

}