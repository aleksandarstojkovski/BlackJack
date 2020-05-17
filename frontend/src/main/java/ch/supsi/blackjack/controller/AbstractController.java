package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.model.GameHandler;

abstract public class AbstractController {

    protected final GameHandler model;

    public AbstractController(GameHandler model) {
        this.model = model;
    }

}
