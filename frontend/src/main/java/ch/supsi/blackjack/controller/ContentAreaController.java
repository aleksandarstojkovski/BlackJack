package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.component.*;
import ch.supsi.blackjack.event.*;
import ch.supsi.blackjack.model.*;
import ch.supsi.blackjack.model.state.RoundBetState;
import ch.supsi.blackjack.model.state.RoundPlayerDealsState;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

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

    public ContentAreaController(GameHandler model) {
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

    public void onGameOver() {
        showMessage("Game Over", "Ouch! You've lost every penny.", RoundResult.LOOSE);
    }

    public void onRoundCompleted(RoundCompletedEvent event) {
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

    public void onPlayerBlackjack(PlayerBlackjackEvent event) {
        FadingStatusMessage.flash(this.notificationArea, "You made Blackjack!");
    }
    public void onPlayerBusted(PlayerBustedEvent event) {
        FadingStatusMessage.flash(this.notificationArea, "You busted!");
    }

    public void onDealerBusted(DealerBustedEvent event) {
        FadingStatusMessage.flash(this.notificationArea, "Dealer busted!");
    }

    private void showMessage(String title, String text, RoundResult status) {
        notificationArea.getStyleClass().set(1, status.name().toLowerCase());
        notificationAreaVisible.set(true);
        notificationTitle.set(title);
        notificationText.set(text);
    }


    public void onNewRound(NewRoundEvent event) {
        clearTable();
        loadAvailableCoins();
        playerBalanceProperty.set(event.getPlayerList().get(0).getCoins());
        betsAreaVisible.set(true);
    }

    public void onNewBet(NewBetEvent event) {
        betsAmountProperty.set(betsAmountProperty.get() + event.getBetValue());
    }

    public void onExitGame(GameFinishedEvent event) {
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

    public void loadAvailableCoins() {
        for (Coin c : model.getCoins()){
            CoinImage img = new CoinImage(c);
            coins.add(img);
        }
    }

    public void onNewGame(GameStartedEvent event) {
        clearTable();
        loadAvailableCoins();
        playerBalanceProperty.set(event.getPlayerList().get(0).getCoins());
        betsAreaVisible.set(true);
    }

    public void onPlayerHand(PlayerHandUpdateEvent event) {
        playerHandProperty.set(event.getValue());
        betsAreaVisible.set(false);
    }

    public void onDealerHand(DealerHandUpdateEvent event) {
        //TODO: should we move this logic in model?
        boolean hideRealHandValue = event.getHandSize()==2 && ((event.getState() instanceof RoundPlayerDealsState) || (event.getState() instanceof RoundBetState));
        if(hideRealHandValue){
            dealerHandProperty.set(event.getValue() - event.getLastCardValue());
        }else {
            dealerHandProperty.set(event.getValue());
        }
        betsAreaVisible.set(false);
    }

    public void onNewCard(NewCardEvent event) {
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

    public void onDealerStart(){
        // show covered card
        dealerCards.get(1).flipCard();
    }

}
