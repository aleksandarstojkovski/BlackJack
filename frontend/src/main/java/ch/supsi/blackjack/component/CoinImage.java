package ch.supsi.blackjack.component;

import javafx.scene.image.Image;

import java.io.InputStream;

public class CoinImage implements Drawable {

    private final Coin coin;
    private Image image;

    public CoinImage(Coin coin) {
        this.coin = coin;
    }

    @SuppressWarnings("SpellCheckingInspection")
    private String getFileName() {
        return String.format("/ch/supsi/blackjack/images/coins/%d.png", coin.getValue());
    }

    @Override
    public Image getImage() {
        if(image == null) {
            InputStream url = getInputStream();
            image = new Image(url);
        }
        return image;
    }

    // package visibility for unit test
    InputStream getInputStream() {
        return this.getClass().getResourceAsStream(getFileName());
    }

    public int getValue() {
        return coin.getValue();
    }

}
