package ch.supsi.blackjack.view;

import ch.supsi.blackjack.controller.AbstractController;
import ch.supsi.blackjack.event.GameFinishedEvent;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

public class FakeView extends AbstractView {
    private final FakeController controller;
    public boolean eventHandled = false;

    // Constructor is not public, use Static Factory Method
    FakeView(FakeController controller) {
        this.controller = controller;
    }

    // Static Factory Method
    public static FakeView create(AbstractController controller, ResourceBundle bundle, String FXML)
            throws InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        return AbstractView.create(FakeView.class, FXML, controller, bundle);
    }

    @EventHandler
    void handleEvent(GameFinishedEvent event) {
        eventHandled = true;
    }
}