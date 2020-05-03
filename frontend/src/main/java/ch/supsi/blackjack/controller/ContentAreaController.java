package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.event.*;
import ch.supsi.blackjack.model.*;
import ch.supsi.blackjack.model.state.RoundBetState;
import ch.supsi.blackjack.model.state.RoundPlayerDealsState;
import ch.supsi.blackjack.view.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.beans.PropertyChangeEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class ContentAreaController extends AbstractController implements Initializable {
    @FXML private VBox betsArea;
    @FXML private VBox notificationArea;
    @FXML private Label dealerHand;
    @FXML private Label playerBalance;
    @FXML private Label playerHand;
    @FXML private Label betsAmount;
    @FXML private ListView<CoinImage> coinsListView;
    @FXML private ListView<CardImage> playerCardListView;
    @FXML private ListView<CardImage> dealerCardListView;

    //TODO: test controller through observables and events
    private final ObservableList<CoinImage> coins = FXCollections.observableArrayList();
    private final ObservableList<CardImage> playerCards = FXCollections.observableArrayList(CardImage.extractor());
    private final ObservableList<CardImage> dealerCards = FXCollections.observableArrayList(CardImage.extractor());

    private final IntegerProperty playerHandProperty = new SimpleIntegerProperty(0);
    private final IntegerProperty dealerHandProperty = new SimpleIntegerProperty(0);
    private final IntegerProperty betsAmountProperty = new SimpleIntegerProperty(0);
    private final IntegerProperty playerBalanceProperty = new SimpleIntegerProperty(0);

    private final BooleanProperty betsAreaVisible = new SimpleBooleanProperty(false);
    private final BooleanProperty notificationAreaVisible = new SimpleBooleanProperty(false);
    private final StringProperty notificationTitle = new SimpleStringProperty();
    private final StringProperty notificationText = new SimpleStringProperty();
    // required for FXML binding
    public StringProperty notificationTitleProperty() {
        return notificationTitle;
    }
    public String getNotificationTitle()
    {
        return notificationTitle.get();
    }
    public StringProperty notificationTextProperty() {
        return notificationText;
    }
    public String getNotificationText()
    {
        return notificationText.get();
    }

    public ContentAreaController(AbstractModel model) {
        super(model);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        betsArea.visibleProperty().bind(betsAreaVisible);
        notificationArea.visibleProperty().bind(notificationAreaVisible);

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
        showMessage("Game Over", "Ouch! You've lost every penny.", RoundResult.LOOSE);
    }

    private void handleRoundCompleted(RoundCompletedEvent event) {
        switch (event.getRoundResult()) {
            case WIN:
                showMessage("Win", "You win.", event.getRoundResult());
                break;
            case DRAW:
                showMessage("Draw", "You draw.", event.getRoundResult());
                break;
            case LOOSE:
                showMessage("Lost", "Dealer Wins.", event.getRoundResult());
                break;
        }

    }

    private void handlePlayerBlackjack(PlayerBlackjackEvent event) {
        FadingStatusMessage.flash(this.notificationArea, "You made Blackjack!");
    }
    private void handlePlayerBusted(PlayerBustedEvent event) {
        FadingStatusMessage.flash(this.notificationArea, "You busted!");
    }

    private void handleDealerBusted(DealerBustedEvent event) {
        FadingStatusMessage.flash(this.notificationArea, "Dealer busted!");
    }

    private void showMessage(String title, String text, RoundResult status) {
        notificationArea.getStyleClass().set(1, status.name().toLowerCase());
        notificationAreaVisible.set(true);
        notificationTitle.set(title);
        notificationText.set(text);
    }


    private void handleNewRound(NewRoundEvent event) {
        clearTable();
        loadAvailableCoins();
        playerBalanceProperty.set(event.getPlayerList().get(0).getCoins());
        betsAreaVisible.set(true);
    }

    private void handleNewBet(NewBetEvent event) {
        betsAmountProperty.set(betsAmountProperty.get() + event.getBetValue());
    }

    private void handleExitGame(GameFinishedEvent event) {
        clearTable();
        betsAreaVisible.set(false);
    }

    private void clearTable() {
        playerCards.clear();
        coins.clear();
        dealerCards.clear();

        betsAmountProperty.set(0);
        playerHandProperty.setValue(0);
        dealerHandProperty.setValue(0);
        notificationAreaVisible.set(false);
    }

    private void handleNewGame(GameStartedEvent event) {
        clearTable();
        loadAvailableCoins();
        playerBalanceProperty.set(event.getPlayerList().get(0).getCoins());
        betsAreaVisible.set(true);
    }

    private void loadAvailableCoins() {
        for (Coin c : model.getCoins()){
            CoinImage img = new CoinImage(c);
            coins.add(img);
        }
    }

    private void handlePlayerHand(PlayerHandUpdateEvent event) {
        playerHandProperty.set(event.getValue());
        betsAreaVisible.set(false);
    }

    private void handleDealerHand(DealerHandUpdateEvent event) {
        //TODO: should we move this logic in model?
        boolean hideRealHandValue = event.getHandSize()==2 && ((event.getState() instanceof RoundPlayerDealsState) || (event.getState() instanceof RoundBetState));
        if(hideRealHandValue){
            dealerHandProperty.set(event.getValue() - event.getLastCardValue());
        }else {
            dealerHandProperty.set(event.getValue());
        }
        betsAreaVisible.set(false);
    }

    private void handleNewCard(NewCardEvent event) {
        Card card = event.getCard();
        if (event.getPlayer() instanceof Dealer){
            // dealer second card must be covered
            boolean covered = dealerCards.size() == 1;
            CardImage img = new CardImage(card, covered);
            dealerCards.add(img);
        } else {
            CardImage img = new CardImage(card);
            playerCards.add(img);
        }
    }

    private void showCoveredCard(DealerStartEvent event){
        dealerCards.get(1).flipCard();
    }

}
