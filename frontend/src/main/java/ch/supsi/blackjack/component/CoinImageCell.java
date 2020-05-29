package ch.supsi.blackjack.component;

import ch.supsi.blackjack.CommandCatalog;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

public class CoinImageCell extends ListCell<CoinImage> {

    final CommandCatalog commandCatalog;

    public final static String[] coinFxIds = { "#coin100", "#coin200", "#coin300", "#coin400", "#coin500" };

    public CoinImageCell(CommandCatalog commandCatalog) {
        this.commandCatalog = commandCatalog;
    }

    @Override
    @SuppressWarnings("SpellCheckingInspection")
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
            // TODO: da cambiare per disaccopiare meglio (forzato la classe)
            button.setOnAction(e -> commandCatalog.execute("bet"+coin.getValue()+"Action") );
            button.setId("coin"+coin.getValue());

            setText(null);
            setGraphic(button);
        }
    }

}
