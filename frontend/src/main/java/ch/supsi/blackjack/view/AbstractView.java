package ch.supsi.blackjack.view;

import ch.supsi.blackjack.controller.AbstractController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class AbstractView implements PropertyChangeListener {
    protected Parent component;
    protected EventRouter eventRouter;

    protected void setComponent(Parent component) { this.component = component; }
    public Parent getComponent() {
        return component;
    }

    // Static Factory Method
    protected static <T extends AbstractView> T create(Class<T> clazz, String resource, AbstractController controller, ResourceBundle bundle)
            throws InstantiationException, IOException, InvocationTargetException, IllegalAccessException {
        if (controller == null) {
            throw new InstantiationException("view controller cannot be null!");
        }

        T view = null;
        for(var ctor : clazz.getDeclaredConstructors()) {
            if(ctor.getParameterCount() == 1 && ctor.getParameterTypes()[0].isAssignableFrom(controller.getClass())) {
                // create the View invoking the constructor that expect a controller as parameter
                view = (T)ctor.newInstance(controller);
                break;
            }
        }

        if(view == null)
            throw new InstantiationException("view constructor must have 1 parameter of type " + controller.getClass());

        view.eventRouter = new EventRouter(view);

        // load the FXML associated to that view
        URL url = AbstractView.class.getClassLoader().getResource(resource);
        if(url == null) {
            throw new InstantiationException("cannot load FXML resource: " + resource);
        }

        FXMLLoader fxmlLoader = new FXMLLoader(url, bundle);
        // don't let the FXMLLoader create a new controller, use the controller passed as parameter
        fxmlLoader.setControllerFactory((Class<?> type) -> controller);
        // set in the View the JavaFX component returned by the fxmlLoader
        view.setComponent(fxmlLoader.load());

        return view;
    }

    /**
     * routing of the PropertyChangeEvent to the proper method (using reflection)
     */
    @Override
    public void propertyChange(PropertyChangeEvent event) {
        eventRouter.route(event);
    }
}
