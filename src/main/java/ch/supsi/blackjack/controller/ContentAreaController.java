package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.event.NewCardEvent;
import ch.supsi.blackjack.event.NewGameEvent;
import ch.supsi.blackjack.event.NewHandEvent;
import ch.supsi.blackjack.model.Card;
import ch.supsi.blackjack.model.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.beans.PropertyChangeEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class ContentAreaController extends AbstractController implements Initializable {
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
        }else if (event instanceof NewHandEvent) {
            handleNewHand((NewHandEvent) event);
        }else if (event instanceof NewGameEvent) {
            handleNewGame((NewGameEvent) event);
        }
        else
            textArea.appendText(event.getClass().getCanonicalName() + "\n");
    }

    private void handleNewGame(NewGameEvent event) {
        textArea.appendText(event.getClass().getCanonicalName() + "\n");
        playerCards.getChildren().clear();
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
