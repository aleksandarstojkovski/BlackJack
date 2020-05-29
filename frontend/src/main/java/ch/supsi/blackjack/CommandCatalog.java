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
        register("bet100Action", new BetCommand(model, 100));
        register("bet200Action", new BetCommand(model, 200));
        register("bet300Action", new BetCommand(model, 300));
        register("bet400Action", new BetCommand(model, 400));
        register("bet500Action", new BetCommand(model, 500));
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
