package ch.supsi.blackjack.view;

import ch.supsi.blackjack.UITest;
import ch.supsi.blackjack.controller.ContentAreaController;
import ch.supsi.blackjack.controller.LogController;
import ch.supsi.blackjack.controller.MenuController;
import ch.supsi.blackjack.model.GameHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

@Category(UITest.class)
public class AbstractViewTest extends ApplicationTest {
    private ResourceBundle bundle;
    private GameHandler model;

    @Before
    public void Setup() {
        bundle = ResourceBundle.getBundle("ch/supsi/blackjack/bundles/blackjack");
        model = Mockito.mock(GameHandler.class);
    }

    @org.junit.Test
    public void createContentAreaView() {
        step("create ContentAreaView", () -> {
            AbstractView view = null;
            try {
                ContentAreaController controller = new ContentAreaController(model);
                view = ContentAreaView.create(controller, bundle);
            } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException | IOException e) {
                e.printStackTrace();
            }

            Assert.assertNotNull(view);
        });
    }

    @org.junit.Test
    public void createLogView() {
        step("create LogView", () -> {
            AbstractView view = null;
            try {
                LogController controller = new LogController(model);
                view = LogView.create(controller, bundle);
            } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException | IOException e) {
                e.printStackTrace();
            }

            Assert.assertNotNull(view);
        });
    }

    @org.junit.Test
    public void createMenuView() {
        step("create MenuView", () -> {
            AbstractView view = null;
            try {
                MenuController controller = new MenuController(model);
                view = MenuView.create(controller, bundle);
            } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException | IOException e) {
                e.printStackTrace();
            }

            Assert.assertNotNull(view);
        });
    }

    private void step(final String step, final Runnable runnable) {
        runnable.run();
    }

}