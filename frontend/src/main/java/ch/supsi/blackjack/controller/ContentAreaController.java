package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.event.*;
import ch.supsi.blackjack.model.*;
import ch.supsi.blackjack.view.CardImage;
import ch.supsi.blackjack.view.CoinImage;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.beans.PropertyChangeEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class ContentAreaController extends AbstractController implements Initializable {

    @FXML private Label dealerHand;
    @FXML private Label playerBalance;
    @FXML private Label playerHand;
    @FXML private Label betsAmount;
    @FXML private VBox betsArea;

    @FXML private ListView<CoinImage> coins;
    @FXML private ListView<CardImage> playerCards;
    @FXML private ListView<CardImage> dealerCards;

    @FXML private TextArea textArea;

    private final IntegerProperty playerHandProperty = new SimpleIntegerProperty(0);
    private final IntegerProperty dealerHandProperty = new SimpleIntegerProperty(0);
    private final IntegerProperty betsAmountProperty = new SimpleIntegerProperty(0);
    private final IntegerProperty playerBalanceProperty = new SimpleIntegerProperty(0);

    public ContentAreaController(Model model) {
        super(model);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dealerCards.setCellFactory(getCardListCell(dealerCards.heightProperty()));
        playerCards.setCellFactory(getCardListCell(playerCards.heightProperty()));
        coins.setCellFactory(getCoinListCell(coins.heightProperty()));
        betsAmount.textProperty().bind(betsAmountProperty.asString());
        playerBalance.textProperty().bind(playerBalanceProperty.asString());
        playerHand.textProperty().bind(playerHandProperty.asString());
        dealerHand.textProperty().bind(dealerHandProperty.asString());
    }

    private Callback<ListView<CardImage>, ListCell<CardImage>> getCardListCell(ReadOnlyDoubleProperty containerHeight) {
        return param -> {
            return new ListCell<>() {
                @Override
                public void updateItem(CardImage drawable, boolean empty) {
                    super.updateItem(drawable, empty);
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        ImageView imageView = new ImageView();
                        imageView.setPreserveRatio(true);
                        imageView.fitHeightProperty().bind(containerHeight.subtract(20));
                        imageView.setImage(drawable.getImage());

                        setText(null);
                        setGraphic(imageView);
                    }
                }
            };
        };
    }

    private Callback<ListView<CoinImage>, ListCell<CoinImage>> getCoinListCell(ReadOnlyDoubleProperty containerHeight) {
        return param -> {
            return new ListCell<>() {
                @Override
                public void updateItem(CoinImage coin, boolean empty) {
                    super.updateItem(coin, empty);
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        Button button = new Button();
                        button.getStyleClass().add("coin");
                        ImageView imageView = new ImageView();
                        imageView.fitHeightProperty().bind(containerHeight.subtract(20));
                        imageView.setPreserveRatio(true);
                        imageView.setImage(coin.getImage());

                        button.setGraphic(imageView);
                        button.setOnAction(e -> model.bet(coin.getValue()) );

                        setText(null);
                        setGraphic(button);
                    }
                }
            };
        };
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

        // log
        textArea.appendText(event.getClass().getCanonicalName() + "\n");
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
        playerHandProperty.setValue(0);
        dealerHandProperty.setValue(0);
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
        playerCards.getItems().clear();
        coins.getItems().clear();
        dealerCards.getItems().clear();
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
            coins.getItems().add(img);
        }
    }

    private void handlePlayerHand(PlayerHandUpdateEvent event) {
        playerHandProperty.set(event.getValue());
        betsArea.setVisible(false);
    }

    private void handleDealerHand(DealerHandUpdateEvent event) {
        if(event.getHandSize()==2){
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
            if (dealerCards.getItems().size()==1) {
                img = new CardImage(card,true);
            }else{
                img = new CardImage(card);
            }
            dealerCards.getItems().add(img);
        } else {
            img = new CardImage(card);
            playerCards.getItems().add(img);
        }
    }

    private void showCoveredCard(DealerStartEvent event){
        dealerCards.getItems().get(1).flipCard();
    }

}
