package ch.supsi.blackjack.view;

import ch.supsi.blackjack.controller.LogController;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

public class LogView extends AbstractView {
    private final LogController controller;

    LogView(LogController controller) {
        this.controller = controller;
    }

    public static LogView create(LogController controller, ResourceBundle bundle) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        return AbstractView.create(LogView.class, "/ch/supsi/blackjack/view/Log.fxml", controller, bundle);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.logEvent(evt);
    }
}
