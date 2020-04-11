package ch.supsi.blackjack.view;

import ch.supsi.blackjack.model.Card;
import javafx.scene.image.Image;

import java.net.URL;

public class CardImage implements Drawable {
    private final Card card;
    private Image image;

    public CardImage(Card card) {
        this.card = card;
    }

    public String getFileName() {
        return String.format("/ch/supsi/blackjack/images/cards/%s_of_%s.png", card.getValue().label, card.getSeed().label);
    }

    @Override
    public Image getImage() {
        if(image == null) {
            URL url = this.getClass().getResource(getFileName());
            image = new Image(url.toString());
        }
        return image;
    }
}
