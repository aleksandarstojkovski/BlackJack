package ch.supsi.blackjack.model;


import ch.supsi.blackjack.event.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {

    private static Model model;
    //private DecksContainer decksContainer;
    private Game game;
    private Dealer dealer;
    private List<Player> playerList;

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
        this.dealer = new Dealer();
        this.playerList = new ArrayList<Player>();
        playerList.add(new Player("Player 1"));
        game = new Game(dealer,(ArrayList<Player>) playerList);
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
        game.getPlayerList().get(0).getHand().addCard(card);
        pcs.firePropertyChange(new NewHandEvent(this, game.getPlayerList().get(0).getHand()));
    }

    @Override
    public void stopCard() {
        pcs.firePropertyChange(new StopCardEvent(this));
    }
}
