package ch.supsi.blackjack.model;

import ch.supsi.blackjack.event.*;
import ch.supsi.blackjack.model.exception.InsufficientCoinsException;
import ch.supsi.blackjack.model.state.GameState;
import ch.supsi.blackjack.model.state.GameStateManager;
import ch.supsi.blackjack.model.state.InitState;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * Here is the main logic of the game.
 * A Round of Blackjack starts betting an amount of money
 * After confirming the bet, each player receives 2 cards
 * Dealer receives 2 cards as well, but one is hidden.
 * Each player defines its own strategy, asking for more cards (hit) or confirming (stand)
 * 21 is the maximum value. In case of 21, the player automatically win the round (Blackjack)
 * Over 21 the player loose the round automatically (Bust)
 * If still in game the turn pass to the next player/dealer
 */
public class Round implements GameStateManager {
    private final List<Player> allPlayers = new ArrayList<>();
    private final Player mainPlayer;
    private final Dealer dealer;

    // updated by model  - indicates that user confirmed the bte
    private boolean betConfirmed = false;
    // updated by model  - indicates that the user chose to stand
    private boolean playerStand = false;

    private GameState currentState;
    private final PropertyChangeSupport pcs;

    public Round(PropertyChangeSupport pcs, Player mainPlayer, Dealer dealer){
        this.pcs = pcs;

        // initial state
        currentState = InitState.instance();
        this.mainPlayer = mainPlayer;
        this.dealer = dealer;

        allPlayers.add(mainPlayer);
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
        // the user can leave the game from any state. He jumps to the InitState
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
        pcs.firePropertyChange(new PlayerBustedEvent(this));
    }

    @Override
    public Hand getDealerHand() {
        return dealer.hand;
    }

    @Override
    public Hand getPlayerHand() {
        return mainPlayer.hand;
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

        RoundResult mainPlayerResult;

        if (mainPlayer.hand.isBusted()){
            // dealer wins
            mainPlayer.hand.takeBets();
            mainPlayerResult = RoundResult.LOOSE;
        } else if (dealer.hand.isBusted()){
            // player wins
            for (Player p : allPlayers){
                int bettedCoins = p.hand.takeBets();
                p.giveCoins(bettedCoins * 2);
            }
            mainPlayerResult = RoundResult.WIN;
        } else if (dealerValue > playerValue){
            // no-one busted
            // dealer wins
            for (Player p : allPlayers){
                p.hand.takeBets();
            }
            mainPlayerResult = RoundResult.LOOSE;
        } else if (dealerValue < playerValue) {
            // player wins
            for (Player p : allPlayers){
                int bettedCoins=p.hand.takeBets();
                p.giveCoins(bettedCoins*2);
            }
            mainPlayerResult = RoundResult.WIN;
        } else {
            //dealerValue == playerValue
            for (Player p : allPlayers){
                int bettedCoins = p.hand.takeBets();
                p.giveCoins(bettedCoins);
            }
            mainPlayerResult = RoundResult.WIN;
        }
        // removes bets from the dealer
        dealer.hand.takeBets();

        pcs.firePropertyChange(new RoundCompletedEvent(this, mainPlayerResult));

        clear();
    }

    private void clear() {
        mainPlayer.discardCards();
        dealer.discardCards();

        betConfirmed = false;
        playerStand = false;
    }

    public void setDealerBusted() {
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

    public void setGameOver() {
        pcs.firePropertyChange(new GameOverEvent(this));
    }

    public void compute(Player player){
        if(player instanceof Dealer){
            dealer.compute(this);
            goNextState();
        } else {
            //ToDo: futura implementazione del PlayerAI
            System.out.println("Questa è instanceof Player, Player = " + player.getNickname()); //Print diagnostico
        }
    }
}
