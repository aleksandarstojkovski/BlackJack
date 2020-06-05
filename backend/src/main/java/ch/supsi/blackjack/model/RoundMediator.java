package ch.supsi.blackjack.model;

import ch.supsi.blackjack.event.*;
import ch.supsi.blackjack.model.exception.InsufficientCoinsException;
import ch.supsi.blackjack.model.exception.InvalidDecksContainerSizeException;
import ch.supsi.blackjack.model.state.round.BetState;
import ch.supsi.blackjack.model.state.round.RoundState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class RoundMediator implements RoundHandler {

    private final GameModel gameModel;
    private RoundState state;
    private DecksContainer decksContainer;
    private final Map<Player,Hand> playerHandMap = new HashMap<>();
    private final List<Player> playersOnly = new ArrayList<>();
    private final Dealer dealer;

    // updated by model  - indicates that user confirmed the bte
    private boolean betConfirmed = false;
    // updated by model  - indicates that the user chose to stand
    private boolean playerStand = false;

    public RoundMediator(GameModel gameModel, String playerNick, String dealerNick){

        Player humanPlayer = new Player(playerNick);
        Dealer dealer = new Dealer(dealerNick);

        playerHandMap.put(humanPlayer, new Hand());
        playerHandMap.put(dealer, new Hand());

        playersOnly.add(humanPlayer);

        this.gameModel = gameModel;
        this.dealer = dealer;

        try {
            this.decksContainer = new DecksContainer.Builder().numberOfDecks(DecksContainer.DEFAULT_NUMBER_OF_DECKS).build();
        } catch (InvalidDecksContainerSizeException e) {
            e.printStackTrace();
        }

        // TODO: we should put this somewhere else in the future
        this.decksContainer.shuffle();

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
        for (Player player : playerHandMap.keySet()) {
            hit(player);
            hit(player);
        }
    }

    public void hit(Player player) {
        Card card = decksContainer.getCard();
        Hand currentHand = playerHandMap.get(player);
        currentHand.addCard(card);

        if(player instanceof Dealer){
            gameModel.firePropertyChange(new DealerHandUpdateEvent(this, currentHand, state));
        }else {
            gameModel.firePropertyChange(new PlayerHandUpdateEvent(this, currentHand));
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
        gameModel.firePropertyChange(new NewRoundEvent(this, playersOnly));
    }

    public void playerHit() {
        hit(getPlayer());
        // calling updateState on PlayerDealsState
        // case1: PlayerDealsState (Player didn't bust)
        // case2: PlayerBustState (Player has busted)
        goNextState();
    }

    public void startRound() {
        state = new BetState(this);
        // case1: BetState (user can start to bet)
        goNextState();

        gameModel.firePropertyChange(new GameStartedEvent(this, playersOnly));
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
            getHand(getPlayer()).addBet(getPlayer().takeCoins(amount));
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

    public void setPlayerBlackjack() {
        gameModel.firePropertyChange(new PlayerBlackjackEvent(this));
    }

    public void setPlayerTwentyOne() {
        gameModel.firePropertyChange(new PlayerTwentyOneEvent(this));
    }

    @SuppressWarnings("SpellCheckingInspection")
    public void setRoundCompleted() {

        Hand playerHand = getHand(getPlayer());
        Hand dealerHand = getHand(getDealer());

        int dealerValue = dealerHand.value();
        int playerValue = playerHand.value();

        RoundResult mainPlayerResult;

        if (playerHand.isBusted()){
            // dealer wins
            playerHand.takeBets();
            mainPlayerResult = RoundResult.LOOSE;
        } else if (dealerHand.isBusted()){
            // player wins
            for (Player p : playerHandMap.keySet()){
                int bettedCoins = getHand(p).takeBets();
                p.giveCoins(bettedCoins * 2);
            }
            mainPlayerResult = RoundResult.WIN;
        } else if (dealerValue > playerValue){
            // no-one busted
            // dealer wins
            for (Player p : playerHandMap.keySet()){
                getHand(p).takeBets();
            }
            mainPlayerResult = RoundResult.LOOSE;
        } else if (dealerValue < playerValue) {
            // player wins
            for (Player p : playerHandMap.keySet()){
                int bettedCoins=getHand(p).takeBets();
                p.giveCoins(bettedCoins*2);
            }
            mainPlayerResult = RoundResult.WIN;
        } else {
            //dealerValue == playerValue
            for (Player p : playerHandMap.keySet()){
                int bettedCoins = getHand(p).takeBets();
                p.giveCoins(bettedCoins);
            }
            mainPlayerResult = RoundResult.WIN;
        }
        // removes bets from the dealer
        dealerHand.takeBets();

        gameModel.firePropertyChange(new RoundCompletedEvent(this, mainPlayerResult));

        clear();
    }

    private void clear() {
        for (Hand hand : playerHandMap.values())
            hand.discardCards();

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
        return getPlayer().hasMoney();
    }

    public boolean isPlayerStand() {
        return playerStand;
    }
    public void updateDealer(){
        gameModel.firePropertyChange(new DealerCompletedEvent(this));
    }

    public void computeDealer() {
        compute(dealer);
    }

    public void setGameOver() {
        gameModel.firePropertyChange(new GameOverEvent(this));
    }

    @SuppressWarnings("SpellCheckingInspection")
    public void compute(Player player){
        if(player instanceof Dealer){
            dealer.compute(this);
            goNextState();
        } else {
            // TODO: futura implementazione del PlayerAI
            System.out.println("Questa è instanceof Player, Player = " + player.getNickname()); //Print diagnostico
        }
    }

    @Override
    public Hand getHand(Player player){
        return playerHandMap.get(player);
    }

    @Override
    public Hand getPlayerHand(){
        return getHand(getPlayer());
    }

    @Override
    public Hand getDealerHand(){
        return getHand(getDealer());
    }

    private Player getPlayer(){
        return playersOnly.get(0);
    }

    private Player getDealer(){
        return dealer;
    }

}
