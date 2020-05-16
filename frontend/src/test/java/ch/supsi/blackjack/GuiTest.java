package ch.supsi.blackjack;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.TextInputControlMatchers;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class GuiTest extends ApplicationTest {

    private static int SLEEP_INTERVAL = 665;

    private int stepNo;

    MainApp main;

    @After
    public void tearDown () throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    private void step(final String step, final Runnable runnable) {
        ++stepNo;
        //LOGGER.info("STEP {}: {}", stepNo, step);
        runnable.run();
        //LOGGER.info("STEP {}: End", stepNo);
    }

    @Override
    public void start(Stage stage) throws Exception {
        main = new MainApp();
        main.start(stage);
    }

    @Test
    public void walkThrough() {
        verifyThat("#new_game", isVisible());
        verifyThat("#textArea", TextInputControlMatchers.hasText(""));

//        testVisibility();
//        testState();
//        testStartAndExit();
    }

    private void testVisibility() {
        step("main scene", () -> {
            verifyThat("#new_game", isVisible());
            // verify console output
            verifyThat("#textArea", TextInputControlMatchers.hasText(""));
        });
    }

    private void testState() {
        step("main scene", () -> {
            verifyThat("#new_game", isEnabled());
        });
    }

    public void testStartAndExit() {
        step("file menu", () -> {
            sleep(SLEEP_INTERVAL);
            clickOn("#new_game");
            verifyThat("#exit_game", isVisible());
            verifyThat("#exit_game", isEnabled());

            sleep(SLEEP_INTERVAL);
            clickOn("#exit_game");
        });
    }

}
