package ch.supsi.blackjack.model;

import ch.supsi.blackjack.event.*;
import ch.supsi.blackjack.model.exception.InsufficientCoinsException;
import ch.supsi.blackjack.model.state.GameState;
import ch.supsi.blackjack.model.state.GameStateManager;
import ch.supsi.blackjack.model.state.InitState;

import java.beans.PropertyChangeSupport;
import java.util.List;

public class Round implements GameStateManager {

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

    private GameState currentState;

    private final Dealer dealer;
    private final List<Player> playerList;
    private final PropertyChangeSupport pcs;

    /*ToDo: player deve diventare una arraylist di player per gestire il multiplayer*/
    public Round(PropertyChangeSupport pcs, Dealer dealer, List<Player> playerList){
        this.pcs = pcs;
        this.dealer = dealer;
        this.playerList = playerList;

        // initial state
        currentState = InitState.instance();
    }

    public void goNextState() {
        currentState.updateState(this);
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }

    public void openRound() {
        betsOpen = false;
        dealsOpen = true;

        hitTwice(dealer);//ToDo: miglioare l'apertura del gioco
        for (Player player : playerList) {
            hitTwice(player);
        }
    }

    private void hitTwice(Player player) {
        hit(player);
        hit(player);
    }

    public void hit(Player currentPlayer) {
        Card card = getDealer().giveCard();
        pcs.firePropertyChange(new NewCardEvent(this, card,currentPlayer));
        currentPlayer.addCard(card);

        if(currentPlayer instanceof Dealer){
            pcs.firePropertyChange(new DealerHandUpdateEvent(this, dealer.hand, this.currentState));
        }else {
            pcs.firePropertyChange(new PlayerHandUpdateEvent(this, currentPlayer.getHandValue()));
        }
    }

    public void exit() {
        setCurrentState(InitState.instance());
        pcs.firePropertyChange(new GameFinishedEvent(this));
    }

    public void next() {
        // calling updateState on InitState
        // case1: BetState (user can start to bet)
        goNextState();
        pcs.firePropertyChange(new NewRoundEvent(this,playerList));
    }

    public void playerHit() {
        hit(this.playerList.get(0));
        // calling updateState on PlayerDealsState
        // case1: PlayerDealsState (Player didn't bust)
        // case1: PlayerBustState (Player has busted)
        goNextState();
    }

    public void start() {
        // calling updateState on InitState
        // case1: BetState (user can start to bet)
        goNextState();

        pcs.firePropertyChange(new GameStartedEvent(this, playerList));
    }

    public void playerStand() {
        playerStand = true;
        // calling updateState on PlayerDealsState
        // case1: PlayerBustState (Player made BlackJack)
        // case1: PlayerDealsState (Player didn't make BlackJack)
        goNextState();
        pcs.firePropertyChange(new StandEvent(this,dealer));
    }

    public void playerBet(int amount) {
        try {
            playerList.get(0).bet(amount);
            pcs.firePropertyChange(new NewBetEvent(this, amount));

            // user can confirm the bet only after at least one coin has been bet
            betsOpen = true;

            // calling updateState on BetState
            // case1: remain in BetState (until user confirms the bet)
            goNextState();
        } catch (InsufficientCoinsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void playerConfirmBet() {
        // this will let BetState know that it can go to the next state
        betConfirmed = true;
        pcs.firePropertyChange(new BetConfirmedEvent(this));

        // calling updateState on BetState
        // case1: SetupTableState (Player confirmed the bet)
        goNextState();
        // calling updateState on SetupTableState
        // case1: TwentyOneState (Player did BlackJack)
        // case2: PlayerDealsState (Player didn't do BlackJack)
        goNextState();
    }

    public void setPlayerBusted() {
        playerBusted = true;
        pcs.firePropertyChange(new PlayerBustedEvent(this));
    }

    public void setPlayerBlackjack() {
        pcs.firePropertyChange(new PlayerBlackjackEvent(this));
    }

    public void setPlayerTwentyone() {
        pcs.firePropertyChange(new PlayerTwentyoneEvent(this));
    }

    public void setRoundCompleted() {

        int dealerValue = dealer.getHandValue();
        int playerValue = playerList.get(0).getHandValue();

        RoundStatus roundStatus;

        if (playerBusted){
            // dealer wins
            for (Player p : playerList ){
                p.takeBets();
            }
            roundStatus = RoundStatus.LOOSE;
        } else if (dealerBusted){
            // player wins
            for (Player p : playerList){
                int bettedCoins = p.takeBets();
                p.giveCoins(bettedCoins * 2);
            }
            roundStatus = RoundStatus.WIN;
        } else if (dealerValue > playerValue){
            // no-one busted
            // dealer wins
            for (Player p : playerList ){
                p.takeBets();
            }
            roundStatus = RoundStatus.LOOSE;
        } else if (dealerValue < playerValue) {
            // player wins
            for (Player p : playerList){
                int bettedCoins=p.takeBets();
                p.giveCoins(bettedCoins*2);
            }
            roundStatus = RoundStatus.WIN;
        } else {
            //dealerValue == playerValue
            for (Player p : playerList){
                int bettedCoins = p.takeBets();
                p.giveCoins(bettedCoins);
            }
            roundStatus = RoundStatus.WIN;
        }
        // removes bets from the dealer
        dealer.takeBets();

        pcs.firePropertyChange(new RoundCompletedEvent(this, roundStatus));

        clear();
    }

    private void clear() {
        dealsOpen = false;
        betsOpen = false;
        playerBusted = false;
        betConfirmed = false;
        playerStand = false;
        dealerBusted = false;
    }

    public void setDealerBusted() {
        dealerBusted = true;
        pcs.firePropertyChange(new DealerBustedEvent(this));
    }

    public boolean isBetConfirmed() {
        return betConfirmed;
    }

    public boolean isPlayerWithMoney() {
        return playerList.get(0).getCoins() > 0;
    }

    public boolean isPlayerStand() {
        return playerStand;
    }
    public void updateDealer(){
        pcs.firePropertyChange(new DealerStartEvent(this,dealer));
        pcs.firePropertyChange(new DealerHandUpdateEvent(this, dealer.hand, this.currentState));
    }
    public void computeDealer() {
        compute(dealer);
    }

    public int getPlayerHandValue() {
        return playerList.get(0).getHandValue();
    }

    public int getDealerHandValue() {
        return dealer.getHandValue();
    }

    public void setGameOver() {
        //model.nextRoundProperty().set(false);
        pcs.firePropertyChange(new GameOverEvent(this));
    }

    public void compute(Player player){
        if(player instanceof Dealer){
            dealer.getAi().compute(this);
            goNextState();
        } else {
            //ToDo: futura implementazione del PlayerAI
            System.out.println("Questa è instanceof Player, Player ID = "+player.getPlayerID()); //Print diagnostico
        }
    }
}
