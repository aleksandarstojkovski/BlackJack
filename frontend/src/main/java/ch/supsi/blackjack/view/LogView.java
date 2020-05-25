package ch.supsi.blackjack.view;

import ch.supsi.blackjack.controller.LogController;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

/**
 * The View is associated to an FXML resource for the definition of the components
 * Methods annotated with @EventHandler are executed through reflection
 */
public class LogView extends AbstractView {

    @SuppressWarnings("SpellCheckingInspection")
    private final static String FXML = "ch/supsi/blackjack/view/Log.fxml";
    private final LogController controller;

    // Constructor is not public, use Static Factory Method
    LogView(LogController controller) {
        this.controller = controller;
    }

    // Static Factory Method
    public static LogView create(LogController controller, ResourceBundle bundle)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        return AbstractView.create(LogView.class, FXML, controller, bundle);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.logEvent(evt);
    }

}
