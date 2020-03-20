package ch.supsi.blackjack.model;


import ch.supsi.blackjack.event.ExitGameEvent;
import ch.supsi.blackjack.event.NewGameEvent;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Model extends AbstractModel {

    private static Model model;
    private DecksContainer decksContainer;

    protected Model() {
        super();
    }

    // singleton
    public static Model instance() {
        if (model == null) {
            model = new Model();
        }

        return model;
    }

    public final BooleanProperty gameRunning = new SimpleBooleanProperty(false);

    public BooleanProperty gameRunningProperty() {
        return gameRunning;
    }

    @Override
    public void newGame() {
        gameRunning.set(true);

        try {
            decksContainer = new DecksContainer(3);
            pcs.firePropertyChange(new NewGameEvent(this));
        } catch (InvalidDecksContainerSizeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exitGame() {
        gameRunning.set(false);

        // finally notify listeners something was done
        pcs.firePropertyChange(new ExitGameEvent(this));
    }
}
