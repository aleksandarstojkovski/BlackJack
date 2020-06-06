package ch.supsi.blackjack.view;

import ch.supsi.blackjack.controller.LogController;
import ch.supsi.blackjack.event.AbstractEvent;
import org.junit.Assert;
import org.mockito.Mockito;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.mockito.Mockito.verify;

public class LogViewTest extends BaseViewTest {
    @org.junit.Test
    public void create() throws InstantiationException, IllegalAccessException, IOException, InvocationTargetException {
        // check create
        LogController controller = Mockito.mock(LogController.class);
        AbstractView view = LogView.create(controller, bundle);
        Assert.assertNotNull(view);

        // check propertyChange
        var event = new AbstractEvent(this);
        view.propertyChange(event);
        verify(controller, Mockito.times(1)).logEvent(event);
    }


}