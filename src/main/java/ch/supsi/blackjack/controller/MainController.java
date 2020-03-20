package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.model.AbstractModel;
import ch.supsi.blackjack.model.Model;

import java.beans.PropertyChangeEvent;

public class MainController extends AbstractController {
    public MainController(Model model) {
        super(model);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
