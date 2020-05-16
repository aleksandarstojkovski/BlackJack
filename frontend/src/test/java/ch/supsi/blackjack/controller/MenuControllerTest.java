package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.model.GameModel;
import org.junit.Assert;
import org.junit.Test;

public class MenuControllerTest {

    @Test
    public void initialState() {
        GameModel mockGameModel = MockGameModel.build();
        MenuController controller = new MenuController(mockGameModel);

        Assert.assertFalse(controller.getDisableNewGame());
        Assert.assertTrue(controller.getDisableBet());
        Assert.assertTrue(controller.getDisableHitAndStand());
        Assert.assertTrue(controller.getDisableExitGame());
    }

    @Test
    public void startGame() {
        // Mock Model
        GameModel mockGameModel = MockGameModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockGameModel);
        controller.onGameStarted();

        Assert.assertTrue(controller.getDisableNewGame());
        Assert.assertTrue(controller.getDisableBet());
        Assert.assertTrue(controller.getDisableHitAndStand());
        Assert.assertFalse(controller.getDisableExitGame());
    }

    @Test
    public void gameFinish() {
        // Mock Model
        GameModel mockGameModel = MockGameModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockGameModel);
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
        GameModel mockGameModel = MockGameModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockGameModel);
        controller.onNewRound();
        Assert.assertTrue(controller.getDisableNextRound());
    }

    @Test
    public void newBet() {
        // Mock Model
        GameModel mockGameModel = MockGameModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockGameModel);
        controller.onNewBet();

        Assert.assertFalse(controller.getDisableBet());
    }

    @Test
    public void confirmBet() {
        // Mock Model
        GameModel mockGameModel = MockGameModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockGameModel);
        controller.onBetConfirmed();

        Assert.assertTrue(controller.getDisableBet());
        Assert.assertFalse(controller.getDisableHitAndStand());

    }

    @Test
    public void standAction() {
        // Mock Model
        GameModel mockGameModel = MockGameModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockGameModel);
        controller.newGameAction(null);
        controller.standAction(null);

        Assert.assertTrue(controller.getDisableHitAndStand());
    }

}
