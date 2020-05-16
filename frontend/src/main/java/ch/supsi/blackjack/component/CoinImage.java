package ch.supsi.blackjack.component;

import ch.supsi.blackjack.model.Coin;
import javafx.scene.image.Image;

import java.net.URL;

public class CoinImage implements Drawable {

    private final Coin coin;
    private Image image;

    public CoinImage(Coin coin) {
        this.coin = coin;
    }

    private String getFileName() {
        return String.format("/ch/supsi/blackjack/images/coins/%d.png", coin.getValue());
    }

    @Override
    public Image getImage() {
        if(image == null) {
            URL url = getUrl();
            image = new Image(url.toString());
        }
        return image;
    }

    // package visibility for unit test
    URL getUrl() {
        return this.getClass().getResource(getFileName());
    }

    public int getValue() {
        return coin.getValue();
    }

}
