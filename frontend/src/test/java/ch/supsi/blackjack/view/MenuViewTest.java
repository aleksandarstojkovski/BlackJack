package ch.supsi.blackjack.view;

import ch.supsi.blackjack.controller.MenuController;
import org.junit.Assert;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class MenuViewTest extends BaseViewTest {

    @org.junit.Test
    public void createMenuView() {
        AbstractView view = null;
        try {
            MenuController controller = new MenuController(commandCatalog);
            view = MenuView.create(controller, bundle);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | IOException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(view);
    }
}