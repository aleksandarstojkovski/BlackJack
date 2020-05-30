package ch.supsi.blackjack;

import ch.supsi.blackjack.component.CoinImageCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import java.util.Random;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.*;

@Category(UITest.class)
public class GuiTest extends ApplicationTest {

    // sleep interval (milliseconds)
    private static final int SLEEP_INTERVAL = 1;
    // random
    private final Random rand = new Random();

    MainApp main;

    @After
    public void tearDown () throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    private void step(final String step, final Runnable runnable) {
        System.out.print(step);
        runnable.run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        main = new MainApp();
        main.start(stage);
    }

    @Test
    public void testInitialState() {
        step("testInitialState", this::initialState);
    }

    @Test
    public void testStartAndExit() {
        step("testStartAndExit", () -> {
            newGame();
            exitGame();
        });
    }

    @Test
    public void testAllCoins(){
        step("testAllCoins", () -> {
            for (String coinFxId : CoinImageCell.coinFxIds) {
                newGame();
                // bet the coin
                sleep(SLEEP_INTERVAL);
                clickOn(coinFxId);
                verifyExitIsVisibleAndEnabled();
                verifyBetIsVisibleAndEnabled();
                exitGame();
            }
        });
    }

    @Test
    public void testStartBetAndExit() {
        step("testStartBetAndExit", () -> {
            newGame();
            betOnRandomCoin();
            confirmBet();
            exitGame();
        });
    }

    @Test
    public void testStartBetHitAndExit() {
        step("testStartBetHitAndExit", () -> {
            newGame();
            betOnRandomCoin();
            confirmBet();
            hit();
            exitGame();
        });
    }

    @Test
    public void testStartBetStandAndExit() {
        step("testStartBetStandAndExit", () -> {
            newGame();
            betOnRandomCoin();
            confirmBet();
            stand();
            exitGame();
        });
    }

    @Test
    public void testNextRound() {
        step("testStartBetStandAndExit", () -> {
            newGame();
            for (int i=0;i<2;i++) {
                betOnRandomCoin();
                confirmBet();
                stand();
                nextRound();
            }
            exitGame();
        });
    }


    private void initialState(){
        verifyNewGameIsVisibleAndEnabled();
        verifyBetIsDisabled();
        verifyNextRoundIsDisabled();
        verifyHitIsDisabled();
        verifyStandIsDisabled();
        verifyExitIsDisabled();
    }

    private void newGame(){
        sleep(SLEEP_INTERVAL);
        clickOn("#new_game");
        verifyNewGameIsDisabled();
        verifyBetIsDisabled();
        verifyHitIsDisabled();
        verifyStandIsDisabled();
        verifyNextRoundIsDisabled();
        verifyExitIsVisibleAndEnabled();
        verifyCoinsAreVisibleAndEnabled();
    }

    private void betOnRandomCoin(){
        sleep(SLEEP_INTERVAL);
        clickOn(CoinImageCell.coinFxIds[rand.nextInt(CoinImageCell.coinFxIds.length)]);
        verifyNewGameIsDisabled();
        verifyBetIsVisibleAndEnabled();
        verifyHitIsDisabled();
        verifyStandIsDisabled();
        verifyNextRoundIsDisabled();
        verifyExitIsVisibleAndEnabled();
        verifyCoinsAreVisibleAndEnabled();
    }

    private void confirmBet(){
        sleep(SLEEP_INTERVAL);
        clickOn("#bet");
        verifyNewGameIsDisabled();
        verifyBetIsDisabled();
        // commented lines are not working if user makes blackjack within the first two cards
        // verifyHitIsVisibleAndEnabled();
        // verifyStandIsVisibleAndEnabled();
        // verifyNextRoundIsDisabled();
        verifyExitIsVisibleAndEnabled();
    }

    private void hit(){
        sleep(SLEEP_INTERVAL);
        clickOn("#get_card");
        // if player busts newGame would be enabled
        // verifyNewGameIsDisabled();
        verifyBetIsDisabled();
        verifyExitIsVisibleAndEnabled();
        verifyBetIsDisabled();
    }

    private void exitGame(){
        sleep(SLEEP_INTERVAL);
        clickOn("#exit_game");
        initialState();
    }

    private void stand(){
        sleep(SLEEP_INTERVAL);
        clickOn("#stop_card");
        verifyExitIsVisibleAndEnabled();
        verifyBetIsDisabled();
        verifyHitIsDisabled();
        // if user busts nextRound would not be enabled
        //verifyNextRoundIsVisibleAndEnabled();
    }

    private void nextRound(){
        sleep(SLEEP_INTERVAL);
        clickOn("#next_round");
        verifyNewGameIsDisabled();
        verifyBetIsDisabled();
        verifyHitIsDisabled();
        verifyStandIsDisabled();
        verifyNextRoundIsDisabled();
        verifyExitIsVisibleAndEnabled();
        verifyCoinsAreVisibleAndEnabled();
    }

    private void verifyCoinsAreVisibleAndEnabled(){
        for (String coinFxId : CoinImageCell.coinFxIds){
            verifyThat(coinFxId, isVisible());
        }
    }

    private void verifyExitIsVisibleAndEnabled(){
        verifyThat("#exit_game", isVisible());
        verifyThat("#exit_game", isEnabled());
    }

    private void verifyBetIsVisibleAndEnabled(){
        verifyThat("#bet", isVisible());
        verifyThat("#bet", isEnabled());
    }

    private void verifyNextRoundIsVisibleAndEnabled(){
        verifyThat("#next_round", isVisible());
        verifyThat("#next_round", isEnabled());
    }

    private void verifyNewGameIsVisibleAndEnabled(){
        verifyThat("#new_game", isVisible());
        verifyThat("#new_game", isEnabled());
    }

    private void verifyHitIsVisibleAndEnabled(){
        verifyThat("#get_card", isVisible());
        verifyThat("#get_card", isEnabled());
    }

    private void verifyStandIsVisibleAndEnabled(){
        verifyThat("#stop_card", isVisible());
        verifyThat("#stop_card", isEnabled());
    }

    private void verifyNewGameIsDisabled(){
        verifyThat("#new_game", isDisabled());
    }

    private void verifyNextRoundIsDisabled(){
        verifyThat("#next_round", isDisabled());
    }

    private void verifyBetIsDisabled(){
        verifyThat("#bet", isDisabled());
    }

    private void verifyHitIsDisabled(){
        verifyThat("#get_card", isDisabled());
    }

    private void verifyStandIsDisabled(){
        verifyThat("#stop_card", isDisabled());
    }

    private void verifyExitIsDisabled(){
        verifyThat("#exit_game", isDisabled());
    }

}
