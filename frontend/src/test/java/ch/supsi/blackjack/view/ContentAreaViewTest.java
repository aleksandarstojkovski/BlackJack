package ch.supsi.blackjack.view;

import ch.supsi.blackjack.UITest;
import ch.supsi.blackjack.controller.ContentAreaController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Category(UITest.class)
public class ContentAreaViewTest extends BaseViewTest {
    @Test
    public void create() throws InstantiationException, IllegalAccessException, IOException, InvocationTargetException {
        ContentAreaController controller = new ContentAreaController(commandCatalog);
        AbstractView view = ContentAreaView.create(controller, bundle);
        Assert.assertNotNull(view);
    }
}