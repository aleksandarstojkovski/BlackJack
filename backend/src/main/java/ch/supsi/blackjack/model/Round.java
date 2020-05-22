package ch.supsi.blackjack.model;

import ch.supsi.blackjack.event.*;
import ch.supsi.blackjack.model.exception.InsufficientCoinsException;
import ch.supsi.blackjack.model.state.round.BetState;
import ch.supsi.blackjack.model.state.round.RoundState;
import java.util.ArrayList;
import java.util.List;

/**
 * Here is the main logic of the round.
 * A Round of Blackjack starts betting an amount of money
 * After confirming the bet, each player receives 2 cards
 * Dealer receives 2 cards as well, but one is hidden.
 * Each player defines its own strategy, asking for more cards (hit) or confirming (stand)
 * 21 is the maximum value. In case of 21, the player automatically win the round (Blackjack)
 * Over 21 the player loose the round automatically (Bust)
 * If still in game the turn pass to the next player/dealer
 */
public class Round implements RoundHandler {
    private final GameModel gameModel;
    private RoundState state;
    private final List<Player> allPlayers = new ArrayList<>();
    private final Player mainPlayer;
    private final Dealer dealer;

    // updated by model  - indicates that user confirmed the bte
    private boolean betConfirmed = false;
    // updated by model  - indicates that the user chose to stand
    private boolean playerStand = false;

    public Round(GameModel gameModel, Player mainPlayer, Dealer dealer){
        this.mainPlayer = mainPlayer;
        this.dealer = dealer;
        allPlayers.add(mainPlayer);
        allPlayers.add(dealer);
        this.gameModel = gameModel;
    }
    @Override
    public void setState(RoundState state){
        this.state = state;
    }
    @Override
    public RoundState getState(){
        return state;
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public void goNextState() {
        state.updateState();
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
        player.addCard(card);

        if(player instanceof Dealer){
            gameModel.firePropertyChange(new DealerHandUpdateEvent(this, player.hand, this.state));
        }else {
            gameModel.firePropertyChange(new PlayerHandUpdateEvent(this, player.hand));
        }
    }

    public void exitRound() {
        // the user can leave the game from any state. He jumps to the GameState InitState
        gameModel.exitGame();
        gameModel.firePropertyChange(new GameFinishedEvent(this));
    }

    public void nextRound() {
        // calling updateState on Continue
        // case1: BetState (user can start to bet)
        goNextState();
        gameModel.firePropertyChange(new NewRoundEvent(this, allPlayers));
    }

    public void playerHit() {
        hit(mainPlayer);
        // calling updateState on PlayerDealsState
        // case1: PlayerDealsState (Player didn't bust)
        // case2: PlayerBustState (Player has busted)
        goNextState();
    }

    public void startRound() {
        state = new BetState(this);
        // case1: BetState (user can start to bet)
        goNextState();

        gameModel.firePropertyChange(new GameStartedEvent(this, allPlayers));
    }

    public void setPlayerStand() {
        playerStand = true;
        // calling updateState on PlayerDealsState
        // case1: PlayerBustState (Player made BlackJack)
        // case2: PlayerDealsState (Player didn't make BlackJack)
        goNextState();
        gameModel.firePropertyChange(new StandEvent(this, dealer));
    }

    public void playerBet(int amount) {
        try {
            mainPlayer.bet(amount);
            gameModel.firePropertyChange(new NewBetEvent(this, amount));

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
        gameModel.firePropertyChange(new BetConfirmedEvent(this));

        // calling updateState on BetState
        // case1: SetupTableState (Player confirmed the bet)
        goNextState();
        // calling updateState on SetupTableState
        // case1: TwentyOneState (Player did BlackJack)
        // case2: PlayerDealsState (Player didn't do BlackJack)
        goNextState();
    }

    public void setPlayerBusted() {
        gameModel.firePropertyChange(new PlayerBustedEvent(this));
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
        gameModel.firePropertyChange(new PlayerBlackjackEvent(this));
    }

    public void setPlayerTwentyOne() {
        gameModel.firePropertyChange(new PlayerTwentyoneEvent(this));
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

        gameModel.firePropertyChange(new RoundCompletedEvent(this, mainPlayerResult));

        clear();
    }

    private void clear() {
        mainPlayer.discardCards();
        dealer.discardCards();

        betConfirmed = false;
        playerStand = false;
    }

    public void setDealerBusted() {
        gameModel.firePropertyChange(new DealerBustedEvent(this));
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
        gameModel.firePropertyChange(new DealerStartEvent(this,dealer));
        gameModel.firePropertyChange(new DealerHandUpdateEvent(this, dealer.hand, this.state));
    }
    public void computeDealer() {
        compute(dealer);
    }

    public void setGameOver() {
        gameModel.firePropertyChange(new GameOverEvent(this));
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
