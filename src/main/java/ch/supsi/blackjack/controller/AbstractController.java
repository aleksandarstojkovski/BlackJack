package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.model.AbstractModel;

abstract public class AbstractController {

    protected AbstractModel model;

    public AbstractController(AbstractModel model) {
        this.model = model;
    }

}
