package ch.supsi.blackjack.model;


import ch.supsi.blackjack.event.*;
import ch.supsi.blackjack.model.exception.InsufficientCoinsException;
import ch.supsi.blackjack.model.state.GameState;
import ch.supsi.blackjack.model.state.InitState;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {

    private static Model model;
    private Stage primaryStage;
    private GameState currentState;
    private Round round;
    private Dealer dealer;
    private List<Player> playerList;
    // updated by the states - activates/deactivates nextRound button
    private final BooleanProperty nextRound = new SimpleBooleanProperty(false);
    // updated by the states - game running boolean
    private final BooleanProperty gameRunning = new SimpleBooleanProperty(false);
    // updated by the states - indicates that users can start to bet
    private final BooleanProperty betsOpen = new SimpleBooleanProperty(false);
    // updated by the states - indicates that users can start to deal
    private final BooleanProperty dealsOpen = new SimpleBooleanProperty(false);
    // updated by the states  - indicates that the user busted
    private final BooleanProperty playerBusted = new SimpleBooleanProperty(false);
    // updated by the states  - indicates that the dealer busted
    private final BooleanProperty dealerBusted = new SimpleBooleanProperty(false);
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
        playerList.add(new Player("Player 1",0));
        round = new Round(dealer,(ArrayList<Player>) playerList);
        // calling updateState on InitState
        // case1: BetState (user can start to bet)
        currentState.updateState(this);
        pcs.firePropertyChange(new NewGameEvent(this, playerList));
    }

    @Override
    public void exitGame() {
        gameRunning.set(false);
        dealsOpen.set(false);
        betsOpen.set(false);
        playerBusted.set(false);
        betConfirmed.set(false);
        atLeastOneCoinBet.set(false);
        playerStand.set(false);
        dealerBusted.set(false);
        currentState = InitState.instance();
        pcs.firePropertyChange(new ExitGameEvent(this));
    }

    @Override
    public void nextRound() {
        // calling updateState on InitState
        // case1: BetState (user can start to bet)
        currentState.updateState(this);
        nextRound.set(false);
        pcs.firePropertyChange(new NewRoundEvent(this,playerList));
    }

    @Override
    public void hit() {
        hitInternal(this.playerList.get(0));
        // calling updateState on PlayerDealsState
        // case1: PlayerDealsState (Player didn't bust)
        // case1: PlayerBustState (Player has busted)
        currentState.updateState(this);
    }

    public void openRound() {
        hitTwice(dealer);//ToDo: miglioare l'apertura del gioco
        for (Player player : model.getPlayerList()) {
            model.hitTwice(player);
        }
    }

    @Override
    public void hitTwice(Player player) {
        for (int i=0;i<2;i++)
            hitInternal(player);
    }

    public void hitInternal(Player currentPlayer) {
        Card card = round.getDealer().giveCard();
        pcs.firePropertyChange(new NewCardEvent(this, card,currentPlayer));
        currentPlayer.getHand().addCard(card);
        // if(playerID==0)
        if(currentPlayer instanceof Dealer){
            pcs.firePropertyChange(new DealerHandUpdateEvent(this, currentPlayer.getHand().value()));
        }else {
            pcs.firePropertyChange(new PlayerHandUpdateEvent(this, currentPlayer.getHand().value()));
        }
    }

    public void compute(Player player){
        if(player instanceof Dealer){
            dealer.getAi().compute(this);
            currentState.updateState(this);
        } else if (player instanceof Player){
            //ToDo: futura implementazione del PlayerAI
            System.out.println("Questa è instanceof Player, Player ID = "+player.getPlayerID()); //Print diagnostico
        }
    }

    @Override
    public void stand() {
        playerStandProperty().set(true);
        // calling updateState on PlayerDealsState
        // case1: PlayerBustState (Player made BlackJack)
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

    public BooleanProperty playerBustedProperty() {
        return playerBusted;
    }

    public BooleanProperty playerStandProperty() {
        return playerStand;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public BooleanProperty nextRoundProperty() {
        return nextRound;
    }

    public void nextState(){
        currentState.updateState(this);
    }

    public BooleanProperty dealerBustedProperty() {
        return dealerBusted;
    }

}
