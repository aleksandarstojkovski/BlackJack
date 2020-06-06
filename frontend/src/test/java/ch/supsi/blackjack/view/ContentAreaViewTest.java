package ch.supsi.blackjack.view;

import ch.supsi.blackjack.controller.ContentAreaController;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ContentAreaViewTest extends BaseViewTest {
    @Test
    public void create() throws InstantiationException, IllegalAccessException, IOException, InvocationTargetException {
        ContentAreaController controller = new ContentAreaController(commandCatalog);
        AbstractView view = ContentAreaView.create(controller, bundle);
        Assert.assertNotNull(view);
    }
}