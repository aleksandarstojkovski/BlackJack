package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.model.AbstractModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.beans.PropertyChangeEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class LogController extends AbstractController implements Initializable {
    public LogController(AbstractModel model) {
        super(model);
    }
    @FXML private TextArea textArea;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // log
        textArea.appendText(evt.getClass().getCanonicalName() + "\n");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
