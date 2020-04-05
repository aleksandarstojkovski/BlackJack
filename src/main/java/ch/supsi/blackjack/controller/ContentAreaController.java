package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.event.*;
import ch.supsi.blackjack.model.Card;
import ch.supsi.blackjack.model.Coin;
import ch.supsi.blackjack.model.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.beans.PropertyChangeEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class ContentAreaController extends AbstractController implements Initializable {

    @FXML public HBox dealerCards;
    @FXML public Label betsAmount;
    @FXML private HBox coins;
    @FXML private HBox playerCards;
    @FXML private TextArea textArea;

    public ContentAreaController(Model model) {
        super(model);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        playerCards.getChildren().clear();
        coins.getChildren().clear();
        dealerCards.getChildren().clear();
        betsAmount.setText("0");
    }

    private void handleNewGame(NewGameEvent event) {
        // log
        textArea.appendText(event.getClass().getCanonicalName() + "\n");
        // clear
        playerCards.getChildren().clear();
        coins.getChildren().clear();
        dealerCards.getChildren().clear();
        // show coins
        for (Coin c : model.getCoins()){
            ImageView imageView = new ImageView(c.getImage());
            imageView.setPreserveRatio(true);
            imageView.fitHeightProperty().bind(coins.heightProperty());
            imageView.setImage(c.getImage());
            BorderPane pane = new BorderPane();
            pane.getChildren().add(imageView);
            pane.setOnMouseClicked(mouseEvent -> {
                model.bet(c.getValue());
            });
            coins.getChildren().add(pane);
        }
    }

    private void handleNewHand(NewHandEvent event) {
        textArea.appendText(event.getHand().toString() + "\n");
    }

    private void handleNewCard(NewCardEvent event) {
        Card card = event.getCard();
        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(playerCards.heightProperty());
        imageView.setImage(card.getImage());
        BorderPane pane = new BorderPane();
        pane.getChildren().add(imageView);
        playerCards.getChildren().add(pane);
        textArea.appendText(card.toString() + "\n");
    }

}
