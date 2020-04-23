package ch.supsi.blackjack.view;

import ch.supsi.blackjack.model.GameHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

public class CoinImageCell extends ListCell<CoinImage> {
    final GameHandler model;
    public CoinImageCell(GameHandler model) {
        this.model = model;
    }

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
            Region listView = this.listViewProperty().get();
            imageView.fitHeightProperty().bind(listView.heightProperty().subtract(20));
            imageView.setPreserveRatio(true);
            imageView.setImage(coin.getImage());

            button.setGraphic(imageView);
            button.setOnAction(e -> model.bet(coin.getValue()) );

            setText(null);
            setGraphic(button);
        }
    }
}
