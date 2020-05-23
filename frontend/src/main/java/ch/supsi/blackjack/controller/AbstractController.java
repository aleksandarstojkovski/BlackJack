package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.command.Command;
import ch.supsi.blackjack.model.GameHandler;
import java.util.HashMap;

abstract public class AbstractController {

    protected final GameHandler model;
    protected final HashMap<String, Command> commandMap;
    public AbstractController(GameHandler model) {
        this.model = model;
        commandMap = new HashMap<>();
    }

    protected void register(String commandName, Command command) {
        commandMap.put(commandName, command);
    }

    protected void execute(String commandName) {
        Command command = commandMap.get(commandName);
        if (command == null) {
            throw new IllegalStateException("no command registered for " + commandName);
        }
        command.execute();
    }
}

