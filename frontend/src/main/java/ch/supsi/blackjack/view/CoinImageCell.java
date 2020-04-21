package ch.supsi.blackjack.view;

import ch.supsi.blackjack.model.GameHandler;
import ch.supsi.blackjack.model.Model;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

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
            ListView listView = this.listViewProperty().get();
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
