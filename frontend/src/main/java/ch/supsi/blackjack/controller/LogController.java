package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.event.*;
import ch.supsi.blackjack.model.Model;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.beans.PropertyChangeEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class LogController extends AbstractController implements Initializable {
    public LogController(Model model) {
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
