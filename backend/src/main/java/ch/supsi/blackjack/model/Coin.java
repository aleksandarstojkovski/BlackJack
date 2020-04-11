package ch.supsi.blackjack.model;

import javafx.scene.image.Image;
import java.net.URL;

public class Coin {

    private Image image;
    private final int value;

    public Coin(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getFileName() {
        return String.format("/ch/supsi/blackjack/images/coins/%d.png", value);
    }

    public Image getImage() {
        if(image == null) {
            URL url = this.getClass().getResource(getFileName());
            image = new Image(url.toString());
        }
        return image;
    }

    @Override
    public String toString() {
        return "Coin{value=" + value + '}';
    }
}
