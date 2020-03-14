package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.model.AbstractModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuController extends AbstractController {
    public MenuController(AbstractModel model) {
        super(model);
    }

    @FXML
    private void startAction(ActionEvent e) {
        System.out.println("START NEW GAME");
    }

    @FXML
    private void exitAction(ActionEvent e) {
        Platform.exit();
    }
}
