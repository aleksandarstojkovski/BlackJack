package ch.supsi.blackjack.component;

import ch.supsi.blackjack.model.GameHandler;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

public class CardImageCell extends ListCell<CardImage> {

    final GameHandler model;

    public CardImageCell(GameHandler model) {
        this.model = model;
    }

    @Override
    public void updateItem(CardImage card, boolean empty) {
        super.updateItem(card, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            ImageView imageView = new ImageView();
            imageView.setPreserveRatio(true);
            Region listView = this.listViewProperty().get();
            imageView.fitHeightProperty().bind(listView.heightProperty().subtract(20));
            imageView.setImage(card.getImage());

            setText(null);
            setGraphic(imageView);
        }
    }
}
