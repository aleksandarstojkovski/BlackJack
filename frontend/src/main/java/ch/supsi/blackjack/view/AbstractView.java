package ch.supsi.blackjack.view;

import ch.supsi.blackjack.controller.AbstractController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class AbstractView implements PropertyChangeListener {
    protected Pane component;

    protected void setComponent(Pane component) { this.component = component; }
    public Parent getComponent() {
        return component;
    }

    // factory method
    protected static <T extends AbstractView> T create(Class<T> clazz, String resource, AbstractController controller, ResourceBundle bundle) throws InstantiationException, IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (controller == null) {
            throw new InstantiationException("view controller cannot be null!");
        }

        T view = clazz.getDeclaredConstructor(controller.getClass()).newInstance(controller);
        URL url = AbstractView.class.getResource(resource);
        FXMLLoader fxmlLoader = new FXMLLoader(url, bundle);
        fxmlLoader.setControllerFactory((Class<?> type) -> controller);
        view.setComponent(fxmlLoader.load());

        return view;
    }
}
