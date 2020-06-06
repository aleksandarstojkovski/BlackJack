package ch.supsi.blackjack.view;

import ch.supsi.blackjack.CommandCatalog;
import ch.supsi.blackjack.event.GameFinishedEvent;
import ch.supsi.blackjack.model.GameModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractViewTest extends ApplicationTest {
    private static final String FakeViewFXML = "FakeView.fxml";

    private ResourceBundle bundle;
    private CommandCatalog commandCatalog;
    private GameModel model;

    @Before
    @SuppressWarnings("SpellCheckingInspection")
    public void Setup() {
        bundle = ResourceBundle.getBundle("ch/supsi/blackjack/bundles/blackjack");
        model = Mockito.mock(GameModel.class);
        commandCatalog = new CommandCatalog(model);
    }

    @Test
    public void create() throws InstantiationException, IllegalAccessException, IOException, InvocationTargetException {
        // check create
        FakeView view = FakeView.create(new FakeController(commandCatalog), bundle, FakeViewFXML);

        // check getComponent
        assertNotNull(view.getComponent());

        // check propertyChange
        view.propertyChange(new GameFinishedEvent(this));
        assertTrue(view.eventHandled);
    }

    @Test
    public void createViewNullController() {
        assertThrows(InstantiationException.class, () -> FakeView.create(null, bundle, FakeViewFXML));
    }

    @Test
    public void createViewNullFXML() {
        assertThrows(InstantiationException.class, () -> FakeView.create(new FakeController(commandCatalog), bundle, "invalid.fxml"));
    }

    @Test
    public void createViewWrongController() {
        assertThrows(InstantiationException.class, () -> FakeView.create(new FakeControllerFail(commandCatalog, 1), bundle, FakeViewFXML));
    }

}