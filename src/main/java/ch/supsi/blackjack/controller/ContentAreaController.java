package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.event.ExitGameEvent;
import ch.supsi.blackjack.event.NewGameEvent;
import ch.supsi.blackjack.model.AbstractModel;
import ch.supsi.blackjack.model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.beans.PropertyChangeEvent;

public class ContentAreaController extends AbstractController {
    @FXML private TextArea textArea;
    public ContentAreaController(Model model) {
        super(model);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event instanceof NewGameEvent) {
            textArea.appendText("NewGameEvent\n");
        } else if (event instanceof ExitGameEvent) {
            textArea.appendText("ExitGameEvent\n");
        }
    }
}
