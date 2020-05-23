package ch.supsi.blackjack;

import ch.supsi.blackjack.command.*;
import ch.supsi.blackjack.model.GameHandler;

import java.util.HashMap;

public class CommandCatalog {
    protected final HashMap<String, Command> commandMap;

    public CommandCatalog(GameHandler model) {
        commandMap = new HashMap<>();
        register("hitAction", new HitCommand(model));
        register("standAction", new StandCommand(model));
        register("betAction", new ConfirmBetCommand(model));
        register("nextRoundAction", new NextRoundCommand(model));
        register("newGameAction", new NewGameCommand(model));
        register("exitGameAction", new ExitGameCommand(model));
        register("bet100Action", new Bet100Command(model));
        register("bet200Action", new Bet200Command(model));
        register("bet300Action", new Bet300Command(model));
        register("bet400Action", new Bet400Command(model));
        register("bet500Action", new Bet500Command(model));

    }

    private void register(String commandName, Command command) {
        commandMap.put(commandName, command);
    }

    public void execute(String commandName) {
        Command command = commandMap.get(commandName);
        if (command == null) {
            throw new IllegalStateException("no command registered for " + commandName);
        }
        command.execute();
    }
}
