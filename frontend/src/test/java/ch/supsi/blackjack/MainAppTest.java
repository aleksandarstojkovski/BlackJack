package ch.supsi.blackjack;

import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class MainAppTest extends ApplicationTest {
    private MainApp app;

    @Override
    public void start(Stage stage) throws Exception {
        app = new MainApp();
        app.start(stage);
    }

    @Test
    public void walkThrough() {
        testLoad();
    }

    private void step(final String step, final Runnable runnable) {
        runnable.run();
    }

    private void testLoad() {
        step("main scene", () -> {
            verifyThat("#textArea", isVisible());
        });
    }
}
