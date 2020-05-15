package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.model.Model;
import org.junit.Assert;
import org.junit.Test;

public class MenuControllerTest {

    @Test
    public void initialState() {
        Model mockModel = MockModel.build();
        MenuController controller = new MenuController(mockModel);

        Assert.assertFalse(controller.getDisableNewGame());
        Assert.assertTrue(controller.getDisableBet());
        Assert.assertTrue(controller.getDisableHitAndStand());
        Assert.assertTrue(controller.getDisableExitGame());
    }

    @Test
    public void startGame() {
        // Mock Model
        Model mockModel = MockModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockModel);
        controller.onGameStarted();

        Assert.assertTrue(controller.getDisableNewGame());
        Assert.assertTrue(controller.getDisableBet());
        Assert.assertTrue(controller.getDisableHitAndStand());
        Assert.assertFalse(controller.getDisableExitGame());
    }

    @Test
    public void gameFinish() {
        // Mock Model
        Model mockModel = MockModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockModel);
        controller.onGameFinished();
        Assert.assertFalse(controller.getDisableNewGame());
        Assert.assertTrue(controller.getDisableExitGame());
        Assert.assertTrue(controller.getDisableHitAndStand());
        Assert.assertTrue(controller.getDisableBet());
        Assert.assertTrue(controller.getDisableNextRound());
    }

    @Test
    public void nextRound() {
        // Mock Model
        Model mockModel = MockModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockModel);
        controller.onNewRound();
        Assert.assertTrue(controller.getDisableNextRound());
    }

    @Test
    public void newBet() {
        // Mock Model
        Model mockModel = MockModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockModel);
        controller.onNewBet();

        Assert.assertFalse(controller.getDisableBet());
    }

    @Test
    public void confirmBet() {
        // Mock Model
        Model mockModel = MockModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockModel);
        controller.onBetConfirmed();

        Assert.assertTrue(controller.getDisableBet());
        Assert.assertFalse(controller.getDisableHitAndStand());
    }

    @Test
    public void standAction() {
        // Mock Model
        Model mockModel = MockModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockModel);
        controller.newGameAction(null);
        controller.standAction(null);

        Assert.assertTrue(controller.getDisableHitAndStand());
    }

}