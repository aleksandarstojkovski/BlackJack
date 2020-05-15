package ch.supsi.blackjack.view;

import ch.supsi.blackjack.controller.MenuController;
import ch.supsi.blackjack.model.GameHandler;
import org.junit.Assert;
import org.mockito.Mockito;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

public class MenuViewTest extends ApplicationTest {
//    @org.junit.Test
    public void walkThrough() {
        create();
    }

    private void create() {
        step("create MenuView", () -> {
            ResourceBundle bundle = ResourceBundle.getBundle("ch/supsi/blackjack/bundles/blackjack");
            GameHandler model = Mockito.mock(GameHandler.class);
            MenuController controller = new MenuController(model);
            MenuView menuView = null;
            try {
                menuView = MenuView.create(controller, bundle);
            } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException | IOException e) {
                e.printStackTrace();
            }

            Assert.assertNotNull(menuView);
        });
    }

    private void step(final String step, final Runnable runnable) {
        runnable.run();
    }

}