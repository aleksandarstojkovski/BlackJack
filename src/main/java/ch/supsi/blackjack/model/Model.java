package ch.supsi.blackjack.model;


import ch.supsi.blackjack.event.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {

    private static Model model;
    private GameState currentState;
    private Game game;
    private Dealer dealer;
    private List<Player> playerList;

    protected Model() {
        super();
        currentState = InitState.instance();
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
    public Dealer getDealer(){
        return dealer;
    }
    public List<Player> getPlayerList(){
        return playerList;
    }
    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }

    @Override
    public void newGame() {
        gameRunning.set(true);
        this.dealer = new Dealer();
        this.playerList = new ArrayList<Player>();
        playerList.add(new Player("Player 1"));
        game = new Game(dealer,(ArrayList<Player>) playerList);
        currentState.updateState(this);
        pcs.firePropertyChange(new NewGameEvent(this));
        //ToDo: implementare il metodo per puntare
        currentState.updateState(this); // messo in attesa del metodo di puntare.
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
        if (game.getPlayerList().get(0).getHand().value()>21){
            currentState.updateState(this);
        }
    }

    @Override
    public void stopCard() {
        currentState.updateState(this);
        pcs.firePropertyChange(new StopCardEvent(this));
    }
}
