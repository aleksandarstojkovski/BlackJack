package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.component.Coin;
import ch.supsi.blackjack.CommandCatalog;
import ch.supsi.blackjack.component.*;
import ch.supsi.blackjack.event.*;
import ch.supsi.blackjack.model.*;
import ch.supsi.blackjack.component.InvalidCoinValueException;
import ch.supsi.blackjack.model.state.round.BetState;
import ch.supsi.blackjack.model.state.round.PlayerDealsState;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

    private final ObservableList<CoinImage> coinImages = FXCollections.observableArrayList();
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

    // required for FXML binding
    public StringProperty notificationTextProperty() {
        return notificationText;
    }
    public String getNotificationText()
    {
        return notificationText.get();
    }
    private final Coin[] coins = new Coin[Coin.Values.length];

    public ContentAreaController(CommandCatalog commandCatalog) {
        super(commandCatalog);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        betsArea.visibleProperty().bind(betsAreaVisible);
        notificationArea.visibleProperty().bind(notificationAreaVisible);

        dealerCardListView.setCellFactory(c -> new CardImageCell());
        dealerCardListView.setItems(dealerCards);

        playerCardListView.setCellFactory(c -> new CardImageCell());
        playerCardListView.setItems(playerCards);

        coinsListView.setCellFactory(c -> new CoinImageCell(commandCatalog));
        coinsListView.setItems(coinImages);

        betsAmount.textProperty().bind(betsAmountProperty.asString());
        playerBalance.textProperty().bind(playerBalanceProperty.asString());
        playerHand.textProperty().bind(playerHandProperty.asString());
        dealerHand.textProperty().bind(dealerHandProperty.asString());

        initCoins();
    }

    private void initCoins() {
        List<Coin> list = new ArrayList<>();
        for (int v : Coin.Values) {
            try {
                list.add(new Coin(v));
            } catch (InvalidCoinValueException e) {
                e.printStackTrace();
            }
        }

        list.toArray(coins);
    }

    public void onGameOver() {
        playerBalanceProperty.set(0);
        clearTable();
        FadingStatusMessage.flash(this.notificationArea, "Ouch! You've lost every penny.");
    }

    public void onRoundCompleted(RoundCompletedEvent event) {
        dealerCards.get(1).flipCard();
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

    public void onPlayerBlackjack() {
        FadingStatusMessage.flash(this.notificationArea, "You made Blackjack!");
    }
    public void onPlayerBusted() {
        FadingStatusMessage.flash(this.notificationArea, "You busted!");
    }

    public void onDealerBusted() {
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
        playerBalanceProperty.set(event.getPlayerList().get(0).getCoinsAmount());
        betsAreaVisible.set(true);
    }

    public void onNewBet(NewBetEvent event) {
        betsAmountProperty.set(betsAmountProperty.get() + event.getBetValue());
    }

    public void onExitGame() {
        clearTable();
        betsAreaVisible.set(false);
    }

    private void clearTable() {
        playerCards.clear();
        coinImages.clear();
        dealerCards.clear();

        betsAmountProperty.set(0);
        playerHandProperty.setValue(0);
        dealerHandProperty.setValue(0);
        notificationAreaVisible.set(false);
    }

    @SuppressWarnings("SpellCheckingInspection")
    public void loadAvailableCoins() {
        // TODO: da rivedere per disaccopiare dal backend
        for (Coin c : coins) {
            CoinImage img = new CoinImage(c);
            coinImages.add(img);
        }
    }

    public void onNewGame(GameStartedEvent event) {
        clearTable();
        loadAvailableCoins();
        playerBalanceProperty.set(event.getPlayerList().get(0).getCoinsAmount());
        betsAreaVisible.set(true);
    }

    public void onPlayerHand(PlayerHandUpdateEvent event) {
        playerHandProperty.set(event.getValue());
        betsAreaVisible.set(false);
        CardImage img = new CardImage(event.getLastCard());
        playerCards.add(img);
    }

    public void onDealerHand(DealerHandUpdateEvent event) {
        // dealer second card must be covered
        boolean covered = dealerCards.size() == 1;
        CardImage img = new CardImage(event.getLastCard(), covered);
        dealerCards.add(img);
        //ToDo: move to Backend
        boolean hideRealHandValue = event.getHandSize()==2 && ((event.getState() instanceof PlayerDealsState) || (event.getState() instanceof BetState));
        if(hideRealHandValue){
            dealerHandProperty.set(event.getValue() - event.getLastCard().getDefaultValue());
        }else {
            dealerHandProperty.set(event.getValue());
        }
        betsAreaVisible.set(false);
    }

    public void onDealerCompleted(){
        // show covered card
        dealerCards.get(1).flipCard();
    }

}
