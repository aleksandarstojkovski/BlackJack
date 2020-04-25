package ch.supsi.blackjack.model;

import ch.supsi.blackjack.event.*;
import ch.supsi.blackjack.model.exception.InsufficientCoinsException;
import ch.supsi.blackjack.model.state.GameState;
import ch.supsi.blackjack.model.state.GameStateManager;
import ch.supsi.blackjack.model.state.InitState;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class Round implements GameStateManager {
    private final Player mainPlayer;
    private final Dealer dealer;
    private final List<Player> allPlayers = new ArrayList<>();

    // TODO: manage AI players
    private final List<Player> aiPlayers;
    private int currentAiPlayerIdx = 0;

    // updated by the states  - indicates that the user busted
    private boolean playerBusted = false;
    // updated by the states  - indicates that the dealer busted
    private boolean dealerBusted = false;
    // updated by model  - indicates that user confirmed the bte
    private boolean betConfirmed = false;
    // updated by model  - indicates that the user chose to stand
    private boolean playerStand = false;

    private GameState currentState;

    private final PropertyChangeSupport pcs;

    /*ToDo: player deve diventare una arraylist di player per gestire il multiplayer*/
    public Round(PropertyChangeSupport pcs, Player mainPlayer, Dealer dealer, List<Player> aiPlayers){
        this.pcs = pcs;

        // initial state
        currentState = InitState.instance();
        this.mainPlayer = mainPlayer;
        this.dealer = dealer;
        this.aiPlayers = aiPlayers;

        allPlayers.add(mainPlayer);
        allPlayers.addAll(aiPlayers);
        allPlayers.add(dealer);
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }

    public void goNextState() {
        currentState.updateState(this);
    }

    public void openRound() {
        // at the beginning of the round, each player receives two cards
        for (Player player : allPlayers) {
            hit(player);
            hit(player);
        }
    }

    public void hit(Player player) {
        Card card = dealer.giveCard();
        pcs.firePropertyChange(new NewCardEvent(this, card, player));
        player.addCard(card);

        if(player instanceof Dealer){
            pcs.firePropertyChange(new DealerHandUpdateEvent(this, player.hand, this.currentState));
        }else {
            pcs.firePropertyChange(new PlayerHandUpdateEvent(this, player.getHandValue()));
        }
    }

    public void exitGame() {
        // from any state the user can leave the game
        setCurrentState(InitState.instance());
        pcs.firePropertyChange(new GameFinishedEvent(this));
    }

    public void nextRound() {
        // calling updateState on InitState
        // case1: BetState (user can start to bet)
        goNextState();
        pcs.firePropertyChange(new NewRoundEvent(this, allPlayers));
    }

    public void playerHit() {
        hit(mainPlayer);
        // calling updateState on PlayerDealsState
        // case1: PlayerDealsState (Player didn't bust)
        // case1: PlayerBustState (Player has busted)
        goNextState();
    }

    public void startGame() {
        // calling updateState on InitState
        // case1: BetState (user can start to bet)
        goNextState();

        pcs.firePropertyChange(new GameStartedEvent(this, allPlayers));
    }

    public void setPlayerStand() {
        playerStand = true;
        // calling updateState on PlayerDealsState
        // case1: PlayerBustState (Player made BlackJack)
        // case1: PlayerDealsState (Player didn't make BlackJack)
        goNextState();
        pcs.firePropertyChange(new StandEvent(this, dealer));
    }

    public void playerBet(int amount) {
        try {
            mainPlayer.bet(amount);
            pcs.firePropertyChange(new NewBetEvent(this, amount));

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

    public void setPlayerTwentyOne() {
        pcs.firePropertyChange(new PlayerTwentyoneEvent(this));
    }

    public void setRoundCompleted() {

        int dealerValue = dealer.getHandValue();
        int playerValue = mainPlayer.getHandValue();

        RoundStatus roundStatus;

        if (playerBusted){
            // dealer wins
            for (Player p : allPlayers ){
                p.takeBets();
            }
            roundStatus = RoundStatus.LOOSE;
        } else if (dealerBusted){
            // player wins
            for (Player p : allPlayers){
                int bettedCoins = p.takeBets();
                p.giveCoins(bettedCoins * 2);
            }
            roundStatus = RoundStatus.WIN;
        } else if (dealerValue > playerValue){
            // no-one busted
            // dealer wins
            for (Player p : allPlayers ){
                p.takeBets();
            }
            roundStatus = RoundStatus.LOOSE;
        } else if (dealerValue < playerValue) {
            // player wins
            for (Player p : allPlayers){
                int bettedCoins=p.takeBets();
                p.giveCoins(bettedCoins*2);
            }
            roundStatus = RoundStatus.WIN;
        } else {
            //dealerValue == playerValue
            for (Player p : allPlayers){
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
        return mainPlayer.getCoins() > 0;
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
        return mainPlayer.getHandValue();
    }

    public int getDealerHandValue() {
        return dealer.getHandValue();
    }

    public void setGameOver() {
        pcs.firePropertyChange(new GameOverEvent(this));
    }

    public void compute(Player player){
        if(player instanceof Dealer){
            dealer.compute(this);
            goNextState();
        } else {
            //ToDo: futura implementazione del PlayerAI
            System.out.println("Questa è instanceof Player, Player ID = "+player.getPlayerID()); //Print diagnostico
        }
    }
}
