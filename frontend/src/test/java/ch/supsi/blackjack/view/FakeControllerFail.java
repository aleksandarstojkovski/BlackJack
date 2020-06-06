package ch.supsi.blackjack.view;

import ch.supsi.blackjack.CommandCatalog;
import ch.supsi.blackjack.controller.AbstractController;

public class FakeControllerFail extends AbstractController {
    public FakeControllerFail(CommandCatalog commandCatalog, Integer wrongParam) {
        super(commandCatalog);
    }
}
