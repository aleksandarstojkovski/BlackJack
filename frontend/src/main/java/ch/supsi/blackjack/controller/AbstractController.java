package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.model.AbstractModel;

import java.beans.PropertyChangeListener;

abstract public class AbstractController implements PropertyChangeListener {

    protected AbstractModel model;

    public AbstractController(AbstractModel model) {
        this.model = model;
    }

}
