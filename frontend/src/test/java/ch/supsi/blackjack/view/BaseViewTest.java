package ch.supsi.blackjack.view;

import ch.supsi.blackjack.CommandCatalog;
import ch.supsi.blackjack.model.GameHandler;
import javafx.embed.swing.JFXPanel;
import org.junit.Before;
import org.mockito.Mockito;

import java.util.ResourceBundle;

abstract class BaseViewTest {
    protected ResourceBundle bundle;
    protected CommandCatalog commandCatalog;
    protected GameHandler model;

    @Before
    @SuppressWarnings("SpellCheckingInspection")
    public void Setup() {
        // WORKAROUND !!!
        // initialize javafx toolkit
        JFXPanel fxPanel = new JFXPanel();

        bundle = ResourceBundle.getBundle("ch/supsi/blackjack/bundles/blackjack");
        model = Mockito.mock(GameHandler.class);
        commandCatalog = new CommandCatalog(model);
    }

}
