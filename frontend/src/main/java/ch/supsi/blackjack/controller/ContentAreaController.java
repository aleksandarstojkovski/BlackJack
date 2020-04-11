package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.event.*;
import ch.supsi.blackjack.model.Card;
import ch.supsi.blackjack.model.Coin;
import ch.supsi.blackjack.model.Model;
import ch.supsi.blackjack.view.CardImage;
import ch.supsi.blackjack.view.CoinImage;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import java.beans.PropertyChangeEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class ContentAreaController extends AbstractController implements Initializable {

    @FXML public ListView<CardImage> dealerCards;
    @FXML public Label betsAmount;
    @FXML public Label playerHand;
    @FXML private ListView<CoinImage> coins;
    @FXML private ListView<CardImage> playerCards;
    @FXML private TextArea textArea;

    public ContentAreaController(Model model) {
        super(model);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        betsAmount.setText("0");

        dealerCards.setCellFactory(getCardListCell(dealerCards.heightProperty()));
        playerCards.setCellFactory(getCardListCell(playerCards.heightProperty()));
        coins.setCellFactory(getCoinListCell(coins.heightProperty()));
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
                        imageView.fitHeightProperty().bind(containerHeight);
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
                        imageView.fitHeightProperty().bind(containerHeight);
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
        } else if (event instanceof NewHandEvent) {
            handleNewHand((NewHandEvent) event);
        } else if (event instanceof NewGameEvent) {
            handleNewGame((NewGameEvent) event);
        } else if (event instanceof ExitGameEvent){
            handleExitGame((ExitGameEvent) event);
        } else if (event instanceof NewBetEvent) {
            handleNewBet((NewBetEvent) event);
        } else{
            textArea.appendText(event.getClass().getCanonicalName() + "\n");
        }
    }

    private void handleNewBet(NewBetEvent event) {
        int newAmout = event.getBetValue()+ Integer.parseInt(betsAmount.getText());
        betsAmount.setText(String.valueOf(newAmout));
    }

    private void handleExitGame(ExitGameEvent event) {
        clearTable();
        betsAmount.setText("0");
    }

    private void clearTable() {
        playerCards.getItems().clear();
        coins.getItems().clear();
        dealerCards.getItems().clear();
    }

    private void handleNewGame(NewGameEvent event) {
        // log
        textArea.appendText(event.getClass().getCanonicalName() + "\n");

        // clear
        clearTable();

        // show coins
        for (Coin c : model.getCoins()){
            CoinImage img = new CoinImage(c);
            coins.getItems().add(img);
        }
    }

    private void handleNewHand(NewHandEvent event) {
        playerHand.setText(String.valueOf(event.getHand().value()) );
    }

    private void handleNewCard(NewCardEvent event) {
        Card card = event.getCard();
        CardImage img = new CardImage(card);

        if (event.getPlayerId()==1) {
            playerCards.getItems().add(img);
        }
        if (event.getPlayerId()==0){
            dealerCards.getItems().add(img);
            //imageView.setRotate(180);
        }
    }


}
