package ch.supsi.blackjack.view;

import ch.supsi.blackjack.event.BetConfirmedEvent;
import ch.supsi.blackjack.event.GameFinishedEvent;
import org.junit.Assert;
import org.junit.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EventRouterTest {

    static class Listener implements PropertyChangeListener {

        private final EventRouter router = new EventRouter(this);
        public boolean eventHandled = false;

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            router.route(evt);
        }

        @EventHandler
        void handleEvent(GameFinishedEvent event) {
            eventHandled = true;
        }
    }

    @Test
    public void eventHandled() {
        Listener listener = new Listener();
        listener.propertyChange(new GameFinishedEvent(this));
        Assert.assertTrue(listener.eventHandled);
    }

    @Test
    public void eventNotHandled() {
        Listener listener = new Listener();
        listener.propertyChange(new BetConfirmedEvent(this));
        Assert.assertFalse(listener.eventHandled);
    }

}