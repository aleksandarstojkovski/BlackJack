package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.CommandCatalog;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import java.beans.PropertyChangeEvent;

public class LogController extends AbstractController {

    public LogController(CommandCatalog commandCatalog) {
        super(commandCatalog);
    }
    @FXML private TextArea textArea;

    public void logEvent(PropertyChangeEvent evt) {
        // log
        textArea.appendText(evt.getClass().getCanonicalName() + "\n");
    }

}
