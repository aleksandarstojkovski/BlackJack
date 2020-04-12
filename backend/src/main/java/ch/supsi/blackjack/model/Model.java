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
    private Round round;
    private Dealer dealer;
    private List<Player> playerList;
    private final BooleanProperty gameRunning = new SimpleBooleanProperty(false);
    // updated by the states - indicates that users can start to bet
    private final BooleanProperty betsOpen = new SimpleBooleanProperty(false);
    // updated by the states - indicates that users can start to deal
    private final BooleanProperty dealsOpen = new SimpleBooleanProperty(false);
    // updated by the states  - indicates that the user burst
    private final BooleanProperty playerBurst = new SimpleBooleanProperty(false);
    // updated by model  - indicates that the user bet at least one coin
    private final BooleanProperty atLeastOneCoinBet = new SimpleBooleanProperty(false);
    // updated by model  - indicates that user confirmed the bte
    private final BooleanProperty betConfirmed = new SimpleBooleanProperty(false);
    // updated by model  - indicates that the user chose to stand
    private final BooleanProperty playerStand = new SimpleBooleanProperty(false);
    private Coin[] coins = {new Coin(100),new Coin(200),new Coin(300), new Coin(400),new Coin(500)};

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
        this.dealer = new Dealer();
        this.playerList = new ArrayList<Player>();
        playerList.add(new Player("Player 1"));
        round = new Round(dealer,(ArrayList<Player>) playerList);
        // calling updateState on InitState
        // case1: BetState (user can start to bet)
        currentState.updateState(this);
        pcs.firePropertyChange(new NewGameEvent(this));
    }

    @Override
    public void exitGame() {
        gameRunning.set(false);
        dealsOpen.set(false);
        betsOpen.set(false);
        playerBurst.set(false);
        betConfirmed.set(false);
        atLeastOneCoinBet.set(false);
        playerStand.set(false);
        currentState = InitState.instance();
        pcs.firePropertyChange(new ExitGameEvent(this));
    }

    @Override
    public void hit(int playerID) {
        hitInternal(playerID);
        // calling updateState on PlayerDealsState
        // case1: PlayerDealsState (Player didn't burst)
        // case1: PlayerBurstState (Player has bursted)
        currentState.updateState(this);
    }
    public void openRound() {
        hitTwice(0);
    }

    @Override
    public void hitTwice(int playerID) {
        for (int i=0; i<2;i++)
            hitInternal(playerID);
    }

    private void hitInternal(int playerID) {
        Card card = round.getDealer().giveCard();
        pcs.firePropertyChange(new NewCardEvent(this, card,playerID));
        if(playerID==0){
            round.getDealer().getHand().addCard(card);
            pcs.firePropertyChange(new DealerHandUpdateEvent(this, round.getDealer().getHand().value()));
        }else {
            round.getPlayerList().get(0).getHand().addCard(card);  //todo: fix per chiata statica
            pcs.firePropertyChange(new PlayerHandUpdateEvent(this, round.getPlayerList().get(0).getHand().value()));
        }
    }

    public void compute(int playerID){
        if(playerID==0){
            for(Card card:dealer.getAi().compute()){
                System.out.println(card);
                pcs.firePropertyChange(new NewCardEvent(this, card,playerID));
                round.getDealer().getHand().addCard(card);
                //pcs.firePropertyChange(new NewHandEvent(this, round.getDealer().getHand()));
            }
            currentState.updateState(this);
        }
    }

            @Override
    public void tableSetupComplete() {
        // calling updateState on SetupTableState
        // case1: BlackJackState (Player made BlackJack)
        // case1: PlayerDealsState (Player didn't make BlackJack)
        currentState.updateState(this);
    }

    @Override
    public void stand() {
        playerStandProperty().set(true);
        // calling updateState on PlayerDealsState
        // case1: PlayerBurstState (Player made BlackJack)
        // case1: PlayerDealsState (Player didn't make BlackJack)
        currentState.updateState(this);
        pcs.firePropertyChange(new StandEvent(this));
        //currentState.updateState(this);
    }

    @Override
    public void bet(int amount) {
        try {
            playerList.get(0).bet(amount);
            pcs.firePropertyChange(new NewBetEvent(this, amount));
            // enables confirmBetBtn (user can confirm the bet only after at least one coin has been bet)
            atLeastOneCoinBet.setValue(true);
            // calling updateState on BetState
            // case1: remain in BetState (until user confirms the bet)
            currentState.updateState(this);
        } catch (InsufficientCoinsException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void confirmBet() {
        // this will let BetState knwo that it can go to the next state
        betConfirmedProperty().set(true);
        // calling updateState on BetState
        // case1: SetupTableState (Player confirmed the bet)
        currentState.updateState(this);
        // calling updateState on SetupTableState
        // case1: TwentyOneState (Player did BlackJack)
        // case2: PlayerDealsState (Player didn't do BlackJack)
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

    public BooleanProperty getAtLeastOneCoinBet() {
        return atLeastOneCoinBet;
    }

    public BooleanProperty betConfirmedProperty() {
        return betConfirmed;
    }

    public BooleanProperty playerBurstProperty() {
        return playerBurst;
    }

    public BooleanProperty playerStandProperty() {
        return playerStand;
    }


}
