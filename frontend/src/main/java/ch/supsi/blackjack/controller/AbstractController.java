package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.model.AbstractModel;
import ch.supsi.blackjack.model.GameHandler;

import java.beans.PropertyChangeListener;

abstract public class AbstractController {

    protected final GameHandler model;

    public AbstractController(GameHandler model) {
        this.model = model;
    }

}
