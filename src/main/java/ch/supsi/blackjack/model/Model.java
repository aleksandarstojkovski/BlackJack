package ch.supsi.blackjack.model;


import ch.supsi.blackjack.event.ExitGameEvent;
import ch.supsi.blackjack.event.NewCardEvent;
import ch.supsi.blackjack.event.NewGameEvent;
import ch.supsi.blackjack.event.StopCardEvent;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Model extends AbstractModel {

    private static Model model;
    private DecksContainer decksContainer;
    private Dealer dealer;
    private Player player;

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
            decksContainer.shuffle();

            pcs.firePropertyChange(new NewGameEvent(this));
        } catch (InvalidDecksContainerSizeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exitGame() {
        gameRunning.set(false);
        pcs.firePropertyChange(new ExitGameEvent(this));
    }

    @Override
    public void getCard() {
        Card card = decksContainer.getCard();
        pcs.firePropertyChange(new NewCardEvent(this, card));
    }

    @Override
    public void stopCard() {
        pcs.firePropertyChange(new StopCardEvent(this));
    }
}
