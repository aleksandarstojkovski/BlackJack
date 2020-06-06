package ch.supsi.blackjack.view;

import ch.supsi.blackjack.UITest;
import ch.supsi.blackjack.controller.MenuController;
import org.junit.Assert;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Category(UITest.class)
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