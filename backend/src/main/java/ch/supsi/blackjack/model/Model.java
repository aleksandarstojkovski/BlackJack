package ch.supsi.blackjack.model;


import ch.supsi.blackjack.event.*;
import ch.supsi.blackjack.model.exception.InsufficientCoinsException;
import ch.supsi.blackjack.model.state.GameState;
import ch.supsi.blackjack.model.state.InitState;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    // singleton
    private static Model instance;

    private Stage primaryStage;
    private GameState currentState;
    private Round round;
    private Dealer dealer;
    private List<Player> playerList;

    // updated by the states - indicates that users can start to bet
    private boolean betsOpen = false;
    // updated by the states - indicates that users can start to deal
    private boolean dealsOpen = false;
    // updated by the states  - indicates that the user busted
    private boolean playerBusted = false;
    // updated by the states  - indicates that the dealer busted
    private boolean dealerBusted = false;
    // updated by model  - indicates that user confirmed the bte
    private boolean betConfirmed = false;
    // updated by model  - indicates that the user chose to stand
    private boolean playerStand = false;


    private final Coin[] coins = {new Coin(100),new Coin(200),new Coin(300), new Coin(400),new Coin(500)};

    protected Model() {
        super();
        // initial state
        currentState = InitState.instance();
     }

    // singleton
    public static Model instance() {
        if (instance == null) {
            instance = new Model();
        }

        return instance;
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }

    @Override
    public void newGame() {
        dealsOpen = false;
        betsOpen = false;
        playerBusted = false;
        dealerBusted = false;
        betConfirmed = false;

        this.dealer = new Dealer();
        this.playerList = new ArrayList<>();
        playerList.add(new Player("Player 1",0));
        round = new Round(dealer, playerList);

        // calling updateState on InitState
        // case1: BetState (user can start to bet)
        currentState.updateState(this);
        pcs.firePropertyChange(new GameStartedEvent(this, playerList));
    }

    @Override
    public void exitGame() {
        currentState = InitState.instance();
        pcs.firePropertyChange(new GameFinishedEvent(this));
    }

    @Override
    public void nextRound() {
        // calling updateState on InitState
        // case1: BetState (user can start to bet)
        currentState.updateState(this);
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
        betsOpen = false;
        dealsOpen = true;

        hitTwice(dealer);//ToDo: miglioare l'apertura del gioco
        for (Player player : playerList) {
            hitTwice(player);
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
        } else {
            //ToDo: futura implementazione del PlayerAI
            System.out.println("Questa è instanceof Player, Player ID = "+player.getPlayerID()); //Print diagnostico
        }
    }

    @Override
    public void stand() {
        playerStand = true;
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

            // user can confirm the bet only after at least one coin has been bet
            betsOpen = true;

            // calling updateState on BetState
            // case1: remain in BetState (until user confirms the bet)
            currentState.updateState(this);
        } catch (InsufficientCoinsException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void confirmBet() {
        // this will let BetState know that it can go to the next state
        betConfirmed = true;
        pcs.firePropertyChange(new BetConfirmedEvent(this));

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

    public void nextState(){
        currentState.updateState(this);
    }

    public void setPlayerBusted() {
        playerBusted = true;
        pcs.firePropertyChange(new PlayerBustedEvent(this));
    }

    public void setPlayerBlackjack() {
//        nextRoundProperty().set(true);
        pcs.firePropertyChange(new PlayerBlackjackEvent(this));
    }

    public void setPlayerTwentyone() {
        pcs.firePropertyChange(new PlayerTwentyoneEvent(this));
    }

    public void setRoundCompleted() {

        int dealerValue = dealer.getHand().value();
        int playerValue = playerList.get(0).getHand().value();

        if (playerBusted){
            // dealer wins
            for (Player p : playerList ){
                p.getHand().takeBets();
            }
        }

        if (dealerBusted){
            // player wins
            for (Player p : playerList){
                int bettedCoins = p.getBettedCoins();
                p.giveCoins(bettedCoins * 2);
            }
        }

        // no-one busted
        if (dealerValue > playerValue && !dealerBusted){
//            alert.setTitle("Lose");
//            alert.setHeaderText("You lose.");
            // dealer wins
            for (Player p : playerList ){
                p.getHand().takeBets();
            }
        } else if (dealerValue < playerValue && !playerBusted) {
//            alert.setTitle("Win");
//            alert.setHeaderText("You win.");
            // player wins
            for (Player p : playerList){
                int bettedCoins=p.getHand().takeBets();
                p.giveCoins(bettedCoins*2);
            }
        } else if (dealerValue == playerValue) {
//            alert.setTitle("Draw");
//            alert.setHeaderText("Draw.");
            int bettedCoins;
            for (Player p : playerList){
                bettedCoins=p.getHand().takeBets();
                p.giveCoins(bettedCoins);
            }
        }
        // removes bets from the dealer
        dealer.getHand().takeBets();

        pcs.firePropertyChange(new RoundCompletedEvent(this));
    }

    public void setDealerBusted() {
        dealerBusted = true;
        pcs.firePropertyChange(new DealerBustedEvent(this));
    }

    public boolean isBetConfirmed() {
        return betConfirmed;
    }

    public boolean hasPlayerMoney() {
        return playerList.get(0).getCoins() > 0;
    }

    public boolean isPlayerStand() {
        return playerStand;
    }

    public void computeDealer() {
        compute(dealer);
    }

    public int getPlayerHandValue() {
        return playerList.get(0).getHand().value();
    }

    public int getDealerHandValue() {
        return dealer.getHand().value();
    }
}
