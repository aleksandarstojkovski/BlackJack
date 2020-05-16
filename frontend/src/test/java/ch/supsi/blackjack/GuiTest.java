package ch.supsi.blackjack;

import ch.supsi.blackjack.model.Coin;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.TextInputControlMatchers;
import java.util.Random;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.*;

@Category(UITest.class)
public class GuiTest extends ApplicationTest {

    private static final int SLEEP_INTERVAL = 10;

    private int stepNo;

    MainApp main;

    Random rand = new Random();

    @After
    public void tearDown () throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    private void step(final String step, final Runnable runnable) {
        ++stepNo;
        runnable.run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        main = new MainApp();
        main.start(stage);
    }

    @Test
    public void walkThrough() {
        testInitialState();
        testStartAndExit();
        testAllCoins();
        testStartBetAndExit();
        testStartBetHitAndExit();
        testStartBetStandAndExit();
    }

    private void testInitialState() {
        step("main scene", () -> {
            verifyNewGameIsVisibleAndEnabled();
            verifyThat("#textArea", TextInputControlMatchers.hasText(""));
        });
    }

    public void testStartAndExit() {
        step("file menu", () -> {

            // new game
            sleep(SLEEP_INTERVAL);
            clickOn("#new_game");
            verifyExitIsVisibleAndEnabled();
            verifyCoinsAreVisibleAndEnabled();

            sleep(SLEEP_INTERVAL);
            clickOn("#exit_game");

        });
    }

    public void testAllCoins(){

        for (String coinFxId : Coin.coinFxIds){
            // new game
            sleep(SLEEP_INTERVAL);
            clickOn("#new_game");
            verifyExitIsVisibleAndEnabled();
            verifyCoinsAreVisibleAndEnabled();

            // bet the coin
            sleep(SLEEP_INTERVAL);
            clickOn(coinFxId);
            verifyExitIsVisibleAndEnabled();
            verifyBetIsVisibleAndEnabled();

            // exit game
            sleep(SLEEP_INTERVAL);
            clickOn("#exit_game");
        }

    }

    public void testStartBetAndExit() {
        step("file menu", () -> {

            // new game
            sleep(SLEEP_INTERVAL);
            clickOn("#new_game");
            verifyExitIsVisibleAndEnabled();
            verifyCoinsAreVisibleAndEnabled();

            // bet 100coins
            sleep(SLEEP_INTERVAL);
            clickOn("#coin100");
            verifyExitIsVisibleAndEnabled();
            verifyBetIsVisibleAndEnabled();

            // confirm bet
            sleep(SLEEP_INTERVAL);
            clickOn("#bet");
            verifyExitIsVisibleAndEnabled();
            verifyBetIsDisabled();

            // exit game
            sleep(SLEEP_INTERVAL);
            clickOn("#exit_game");

        });
    }

    private void testStartBetHitAndExit() {
        step("file menu", () -> {

            // new game
            sleep(SLEEP_INTERVAL);
            clickOn("#new_game");
            verifyExitIsVisibleAndEnabled();
            verifyCoinsAreVisibleAndEnabled();

            // bet on a random coin
            sleep(SLEEP_INTERVAL);
            clickOn(Coin.coinFxIds[rand.nextInt(Coin.coinFxIds.length)]);
            verifyExitIsVisibleAndEnabled();
            verifyBetIsVisibleAndEnabled();

            // confirm bet
            sleep(SLEEP_INTERVAL);
            clickOn("#bet");
            verifyExitIsVisibleAndEnabled();
            verifyBetIsDisabled();

            // hit
            sleep(SLEEP_INTERVAL);
            clickOn("#get_card");
            verifyExitIsVisibleAndEnabled();
            verifyBetIsDisabled();

            // exit game
            sleep(SLEEP_INTERVAL);
            clickOn("#exit_game");

        });
    }

    public void testStartBetStandAndExit() {
        step("file menu", () -> {

            // new game
            sleep(SLEEP_INTERVAL);
            clickOn("#new_game");
            verifyExitIsVisibleAndEnabled();
            verifyCoinsAreVisibleAndEnabled();

            // bet on a random coin
            sleep(SLEEP_INTERVAL);
            clickOn(Coin.coinFxIds[rand.nextInt(Coin.coinFxIds.length)]);
            verifyExitIsVisibleAndEnabled();
            verifyBetIsVisibleAndEnabled();

            // confirm bet
            sleep(SLEEP_INTERVAL);
            clickOn("#bet");
            verifyExitIsVisibleAndEnabled();
            verifyBetIsDisabled();

            // stand
            sleep(SLEEP_INTERVAL);
            clickOn("#stop_card");
            verifyExitIsVisibleAndEnabled();
            verifyBetIsDisabled();
            verifyNextRoundIsVisibleAndEnabled();

            // next round
            sleep(SLEEP_INTERVAL);
            clickOn("#next_round");
            verifyExitIsVisibleAndEnabled();
            verifyBetIsDisabled();
            verifyNextRoundIsDisabled();

            // exit game
            sleep(SLEEP_INTERVAL);
            clickOn("#exit_game");

        });
    }

    public void verifyCoinsAreVisibleAndEnabled(){
        for (String coinFxId : Coin.coinFxIds){
            verifyThat(coinFxId, isVisible());
        }
    }

    public void verifyExitIsVisibleAndEnabled(){
        verifyThat("#exit_game", isVisible());
        verifyThat("#exit_game", isEnabled());
    }

    public void verifyBetIsVisibleAndEnabled(){
        verifyThat("#bet", isVisible());
        verifyThat("#bet", isEnabled());
    }

    public void verifyBetIsDisabled(){
        verifyThat("#bet", isDisabled());
    }

    public void verifyNextRoundIsVisibleAndEnabled(){
        verifyThat("#next_round", isVisible());
        verifyThat("#next_round", isEnabled());
    }

    public void verifyNextRoundIsDisabled(){
        verifyThat("#next_round", isDisabled());
    }

    public void verifyNewGameIsVisibleAndEnabled(){
        verifyThat("#new_game", isVisible());
        verifyThat("#new_game", isEnabled());
    }

}
