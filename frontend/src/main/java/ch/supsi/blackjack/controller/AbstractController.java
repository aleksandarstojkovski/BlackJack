package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.CommandCatalog;

abstract public class AbstractController {

    protected final CommandCatalog commandCatalog;

    public AbstractController(CommandCatalog commandCatalog) {
        this.commandCatalog = commandCatalog;

    }

}



