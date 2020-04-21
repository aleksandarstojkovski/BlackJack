package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.event.*;
import ch.supsi.blackjack.model.*;
import ch.supsi.blackjack.model.state.BetState;
import ch.supsi.blackjack.model.state.PlayerDealsState;
import ch.supsi.blackjack.view.CardImage;
import ch.supsi.blackjack.view.CardImageCell;
import ch.supsi.blackjack.view.CoinImage;
import ch.supsi.blackjack.view.CoinImageCell;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.beans.PropertyChangeEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class ContentAreaController extends AbstractController implements Initializable {

    @FXML private Label dealerHand;
    @FXML private Label playerBalance;
    @FXML private Label playerHand;
    @FXML private Label betsAmount;
    @FXML private VBox betsArea;
    @FXML private ListView<CoinImage> coinsListView;
    @FXML private ListView<CardImage> playerCardListView;
    @FXML private ListView<CardImage> dealerCardListView;

    //TODO: test controller through observables and events
    private final ObservableList<CoinImage> coins = FXCollections.observableArrayList();
    private final ObservableList<CardImage> playerCards = FXCollections.observableArrayList();
    private final ObservableList<CardImage> dealerCards = FXCollections.observableArrayList();

    private final IntegerProperty playerHandProperty = new SimpleIntegerProperty(0);
    private final IntegerProperty dealerHandProperty = new SimpleIntegerProperty(0);
    private final IntegerProperty betsAmountProperty = new SimpleIntegerProperty(0);
    private final IntegerProperty playerBalanceProperty = new SimpleIntegerProperty(0);

    private final BooleanProperty betsAreaVisible = new SimpleBooleanProperty(false);

    public ContentAreaController(Model model) {
        super(model);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dealerCardListView.setCellFactory(c -> new CardImageCell(model));
        dealerCardListView.setItems(dealerCards);

        playerCardListView.setCellFactory(c -> new CardImageCell(model));
        playerCardListView.setItems(playerCards);

        coinsListView.setCellFactory(c -> new CoinImageCell(model));
        coinsListView.setItems(coins);

        betsAmount.textProperty().bind(betsAmountProperty.asString());
        playerBalance.textProperty().bind(playerBalanceProperty.asString());
        playerHand.textProperty().bind(playerHandProperty.asString());
        dealerHand.textProperty().bind(dealerHandProperty.asString());
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        
        if (event instanceof NewCardEvent) {
            handleNewCard((NewCardEvent) event);
        } else if (event instanceof DealerHandUpdateEvent) {
            handleDealerHand((DealerHandUpdateEvent) event);
        } else if (event instanceof PlayerHandUpdateEvent) {
            handlePlayerHand((PlayerHandUpdateEvent) event);
        } else if (event instanceof GameStartedEvent) {
            handleNewGame((GameStartedEvent) event);
        } else if (event instanceof GameFinishedEvent){
            handleExitGame((GameFinishedEvent) event);
        } else if (event instanceof NewBetEvent) {
            handleNewBet((NewBetEvent) event);
        } else if (event instanceof NewRoundEvent){
            handleNewRound((NewRoundEvent) event);
        } else if (event instanceof PlayerBustedEvent){
            handlePlayerBusted((PlayerBustedEvent) event);
        } else if (event instanceof PlayerBlackjackEvent){
            handlePlayerBlackjack((PlayerBlackjackEvent) event);
        } else if (event instanceof RoundCompletedEvent){
            handleRoundCompleted((RoundCompletedEvent) event);
        } else if (event instanceof DealerBustedEvent){
            handleDealerBusted((DealerBustedEvent) event);
        } else if (event instanceof GameOverEvent) {
            handleGameOver();
        } else if (event instanceof DealerStartEvent) {
            showCoveredCard((DealerStartEvent) event);
        }

    }

    private void handleGameOver() {
        // TODO: temp dialog
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("Ouch! You've lost every penny.");
        alert.setContentText("Don't worry, you can play again :-).");
        alert.showAndWait();
    }

    private void handleRoundCompleted(RoundCompletedEvent event) {
        switch (event.getRoundStatus()) {
            case WIN:
                showAlert("Win", "You win.");
                break;
            case DRAW:
                showAlert("Draw", "You draw");
                break;
            case LOOSE:
                showAlert("Loose", "You loose");
                break;
        }

    }

    private void handlePlayerBlackjack(PlayerBlackjackEvent event) {
        // TODO: temp dialog
        showAlert("Blackjack", "You made Blackjack!");
    }
    private void handlePlayerBusted(PlayerBustedEvent event) {
        // TODO: temp dialog
        showAlert("Bust", "You busted.");
    }

    private void handleDealerBusted(DealerBustedEvent event) {
        showAlert("Bust", "Dealer busted.");
    }

    private void showAlert(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(text);
        alert.showAndWait();
    }


    private void handleNewRound(NewRoundEvent event) {
        clearTable();
        loadAvailableCoins();
        playerBalanceProperty.set(event.getPlayerList().get(0).getCoins());
        betsArea.setVisible(true);
    }

    private void handleNewBet(NewBetEvent event) {
        betsAmountProperty.set(betsAmountProperty.get() + event.getBetValue());
    }

    private void handleExitGame(GameFinishedEvent event) {
        clearTable();
        betsArea.setVisible(false);
    }

    private void clearTable() {
        playerCards.clear();
        coins.clear();
        dealerCards.clear();

        betsAmountProperty.set(0);
        playerHandProperty.setValue(0);
        dealerHandProperty.setValue(0);
    }

    private void handleNewGame(GameStartedEvent event) {
        clearTable();
        loadAvailableCoins();
        playerBalanceProperty.set(event.getPlayerList().get(0).getCoins());
        betsArea.setVisible(true);
    }

    private void loadAvailableCoins() {
        for (Coin c : model.getCoins()){
            CoinImage img = new CoinImage(c);
            coins.add(img);
        }
    }

    private void handlePlayerHand(PlayerHandUpdateEvent event) {
        playerHandProperty.set(event.getValue());
        betsArea.setVisible(false);
    }

    private void handleDealerHand(DealerHandUpdateEvent event) {
        if(event.getHandSize()==2 && ((event.getCurrentState() instanceof PlayerDealsState) || (event.getCurrentState() instanceof BetState))){
            dealerHandProperty.set(event.getValue()-event.getLastCardValue());
        }else {
            dealerHandProperty.set(event.getValue());
        }
        betsArea.setVisible(false);
    }

    private void handleNewCard(NewCardEvent event) {
        Card card = event.getCard();
        CardImage img;
        if (event.getPlayer() instanceof Dealer){
            if (dealerCards.size()==1) {
                img = new CardImage(card,true);
            }else{
                img = new CardImage(card);
            }
            dealerCards.add(img);
        } else {
            img = new CardImage(card);
            playerCards.add(img);
        }
    }

    private void showCoveredCard(DealerStartEvent event){
        dealerCards.get(1).flipCard();
    }

}
