package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.event.NewCardEvent;
import ch.supsi.blackjack.event.NewGameEvent;
import ch.supsi.blackjack.event.NewHandEvent;
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
        if (event instanceof NewCardEvent) {
            textArea.appendText(((NewCardEvent)event).getCard().toString() + "\n");
        }else if (event instanceof NewHandEvent) {
            textArea.appendText(((NewHandEvent)event).getHand().toString() + "\n");
        }
        else
            textArea.appendText(event.getClass().getCanonicalName() + "\n");
    }
}
