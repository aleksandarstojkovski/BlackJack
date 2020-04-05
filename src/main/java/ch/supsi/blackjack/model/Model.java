package ch.supsi.blackjack.model;


import ch.supsi.blackjack.event.*;
import ch.supsi.blackjack.model.exception.InsufficientCoinsException;
import ch.supsi.blackjack.model.state.GameState;
import ch.supsi.blackjack.model.state.InitState;
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
    private final BooleanProperty gameRunning = new SimpleBooleanProperty(false);
    private final BooleanProperty betsOpen = new SimpleBooleanProperty(false);
    private final BooleanProperty dealsOpen = new SimpleBooleanProperty(false);
    private Coin[] coins = {new Coin(1),new Coin(2),new Coin(5), new Coin(10),new Coin(15),new Coin(25),new Coin(50),new Coin(100)};

    protected Model() {
        super();
        // initial state
        currentState = InitState.instance();
     }

    // singleton
    public static Model instance() {
        if (model == null) {
            model = new Model();
        }

        return model;
    }

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
        // InitState to BetState
        currentState.updateState(this);
        pcs.firePropertyChange(new NewGameEvent(this));
    }

    @Override
    public void exitGame() {
        gameRunning.set(false);
        dealsOpen.set(false);
        betsOpen.set(false);
        pcs.firePropertyChange(new ExitGameEvent(this));
        currentState = InitState.instance();
    }

    @Override
    public void getCard() {
        Card card = game.getDealer().giveCard();
        pcs.firePropertyChange(new NewCardEvent(this, card));
        game.getPlayerList().get(0).getHand().addCard(card);
        pcs.firePropertyChange(new NewHandEvent(this, game.getPlayerList().get(0).getHand()));
    }

    @Override
    public void getCards(int quanitty) {
        for (int i=0; i<quanitty;i++)
            getCard();
    }

    @Override
    public void tableSetupComplete() {
        // SetupTableState to PlayerDealsState
        currentState.updateState(this);
    }

    @Override
    public void stopCard() {
        currentState.updateState(this);
        pcs.firePropertyChange(new StopCardEvent(this));
    }

    @Override
    public void bet(int amount) {
        try {
            playerList.get(0).bet(amount);
            pcs.firePropertyChange(new NewBetEvent(this, amount));
            // TODO: maybe we need a new state that highlights that the user betted at least one time
            model.betsOpenProperty().setValue(true);
        } catch (InsufficientCoinsException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void confirmBet() {
        // BetState to SetupTableState
        currentState.updateState(this);
        // SetupTableState to PlayerDealsState
        currentState.updateState(this);
    }

    @Override
    public Coin[] getCoins() {
        return coins;
    }


    public BooleanProperty betsOpenProperty() {
        return betsOpen;
    }

    public BooleanProperty dealsOpenProperty() {
        return dealsOpen;
    }

}
