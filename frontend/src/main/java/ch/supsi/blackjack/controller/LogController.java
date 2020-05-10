package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.model.AbstractModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.beans.PropertyChangeEvent;

public class LogController extends AbstractController {
    public LogController(AbstractModel model) {
        super(model);
    }
    @FXML private TextArea textArea;

    public void logEvent(PropertyChangeEvent evt) {
        // log
        textArea.appendText(evt.getClass().getCanonicalName() + "\n");
    }
}
