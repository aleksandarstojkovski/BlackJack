package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.model.AbstractModel;

import java.beans.PropertyChangeListener;

abstract public class AbstractController {

    protected final AbstractModel model;

    public AbstractController(AbstractModel model) {
        this.model = model;
    }

}
