package ch.supsi.blackjack;

import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.TextInputControlMatchers;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class GuiTest extends ApplicationTest {

    private static int SLEEP_INTERVAL = 665;

    private int stepNo;

    MainApp main;

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
        testVisibility();
        testState();
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

}
