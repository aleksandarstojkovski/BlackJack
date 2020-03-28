package ch.supsi.blackjack.model;


import ch.supsi.blackjack.event.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Model extends AbstractModel {

    private static Model model;
    //private DecksContainer decksContainer;
    private Game game;
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
        game = new Game();
        pcs.firePropertyChange(new NewGameEvent(this));
    }

    @Override
    public void exitGame() {
        gameRunning.set(false);
        pcs.firePropertyChange(new ExitGameEvent(this));
    }

    @Override
    public void getCard() {
        Card card = game.getDealer().giveCard();
        pcs.firePropertyChange(new NewCardEvent(this, card));
        game.getPlayer().getPlayerHand().addCard(card);
        pcs.firePropertyChange(new NewHandEvent(this, game.getPlayer().getPlayerHand()));
    }

    @Override
    public void stopCard() {
        pcs.firePropertyChange(new StopCardEvent(this));
    }
}
