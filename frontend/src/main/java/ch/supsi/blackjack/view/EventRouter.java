package ch.supsi.blackjack.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;

/**
 * Event Router
 * given a {@link java.beans.PropertyChangeListener } object
 * invokes the right handler of the {@link java.beans.PropertyChangeEvent } instance
 * example:
 *       @EventHandler
 *       void handleEvent(NewBetEvent event) { ... }
 */
public class EventRouter {
    private final PropertyChangeListener listener;

    public EventRouter(PropertyChangeListener listener) {
        this.listener = listener;
    }

    /**
     * find a method in listener that is annotated as @EventHandler
     * the method should have 1 parameter of the concrete PropertyChangeEvent type
     * @param event
     */
    public void route(PropertyChangeEvent event) {
        try {
            for(var method : listener.getClass().getDeclaredMethods()) {
                if(method.getAnnotation(EventHandler.class) != null &&
                        method.getParameterCount() == 1 &&
                        method.getParameterTypes()[0].isAssignableFrom(event.getClass())) {
                    // if present invoke otherwise do nothing
                    method.invoke(listener, event);
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
