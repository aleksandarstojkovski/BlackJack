package ch.supsi.blackjack.view;

import ch.supsi.blackjack.CommandCatalog;
import ch.supsi.blackjack.controller.AbstractController;
import ch.supsi.blackjack.controller.ContentAreaController;
import ch.supsi.blackjack.controller.LogController;
import ch.supsi.blackjack.controller.MenuController;
import ch.supsi.blackjack.model.GameHandler;
import org.junit.Assert;
import org.junit.Before;
import org.mockito.Mockito;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@Category(UITest.class)
public class AbstractViewTest extends ApplicationTest {

    private ResourceBundle bundle;
    private CommandCatalog commandCatalog;

    static class FakeController extends AbstractController {
        public FakeController(CommandCatalog commandCatalog) {
            super(commandCatalog);
        }
    }

    static class FakeView extends AbstractView {
        private final static String FXML = "invalid.fxml";
        private final FakeController controller;

        // Constructor is not public, use Static Factory Method
        FakeView(FakeController controller) {
            this.controller = controller;
        }

        // Static Factory Method
        public static FakeView create(FakeController controller, ResourceBundle bundle)
                throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
            return AbstractView.create(FakeView.class, FXML, controller, bundle);
        }

    }

    @Before
    @SuppressWarnings("SpellCheckingInspection")
    public void Setup() {
        bundle = ResourceBundle.getBundle("ch/supsi/blackjack/bundles/blackjack");
        GameHandler model = Mockito.mock(GameHandler.class);
        commandCatalog = new CommandCatalog(model);
    }

    @org.junit.Test
    public void createViewNullController() {
        assertThrows(InstantiationException.class, () -> FakeView.create(null, bundle));
    }

    @org.junit.Test
    public void createViewNullFXML() {
        assertThrows(InstantiationException.class, () -> FakeView.create(new FakeController(commandCatalog), bundle));
    }

    @org.junit.Test
    public void createContentAreaView() {
//        step("create ContentAreaView", () -> {
            AbstractView view = null;
            try {
                ContentAreaController controller = new ContentAreaController(commandCatalog);
                view = ContentAreaView.create(controller, bundle);
            } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException | IOException e) {
                e.printStackTrace();
                fail();
            }

            Assert.assertNotNull(view);
//        });
    }

    @org.junit.Test
    public void createLogView() {
//        step("create LogView", () -> {
            AbstractView view = null;
            try {
                LogController controller = new LogController(commandCatalog);
                view = LogView.create(controller, bundle);
            } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException | IOException e) {
                e.printStackTrace();
                fail();
            }

            Assert.assertNotNull(view);
//        });
    }

    @org.junit.Test
    public void createMenuView() {
//        step("create MenuView", () -> {
            AbstractView view = null;
            try {
                MenuController controller = new MenuController(commandCatalog);
                view = MenuView.create(controller, bundle);
            } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException | IOException e) {
                e.printStackTrace();
            }

            Assert.assertNotNull(view);
//        });
    }

//    private void step(final String step, final Runnable runnable) {
//        runnable.run();
//    }

}