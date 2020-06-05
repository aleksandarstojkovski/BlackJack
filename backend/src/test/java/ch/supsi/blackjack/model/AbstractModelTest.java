package ch.supsi.blackjack.model;

import ch.supsi.blackjack.event.AbstractEvent;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.beans.PropertyChangeListener;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AbstractModelTest {
    static class SimpleModel extends AbstractModel { }

    @Test
    void test() {
        var model = new SimpleModel();
        PropertyChangeListener listener = Mockito.mock(PropertyChangeListener.class);
        model.addPropertyChangeListener(listener);

        var event = new AbstractEvent(model);
        model.firePropertyChange(event);
        verify(listener, times(1)).propertyChange(event);

        model.removePropertyChangeListener(listener);
        model.firePropertyChange(event);
        verify(listener, times(1)).propertyChange(event);
    }

}