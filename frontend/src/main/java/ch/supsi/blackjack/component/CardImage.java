package ch.supsi.blackjack.component;

import ch.supsi.blackjack.model.Card;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import javafx.util.Callback;

import java.net.URL;

public class CardImage implements Drawable {

    private final BooleanProperty covered;
    private final Card card;
    private Image imageFront;
    private Image imageBack;

    public CardImage(Card card) {
        this(card, false);
    }
    public CardImage(Card card, boolean covered) {
        this.card = card;
        this.covered = new SimpleBooleanProperty(covered);
    }

    // this let the ListView automatically refresh on covered change status
    public static Callback<CardImage, Observable[]> extractor() {
        return (CardImage c) -> new Observable[]{ c.covered };
    }

    private String getFileName() {
        if(covered.get()){
            return String.format("/ch/supsi/blackjack/images/cards/back_%s.png", card.getBack().toString().toLowerCase());
        }else{
            return String.format("/ch/supsi/blackjack/images/cards/%s_of_%s.png", card.getValue().getLabel(), card.getSeed().getLabel());
        }
    }

    @Override
    public Image getImage(){
       if(covered.get()){
           return getImageBack();
       }else{
           return getImageFront();
       }
    }

    private Image loadImage() {
        URL url = getUrl();
        return new Image(url.toString());
    }

    // package visibility for unit test
    URL getUrl() {
        return this.getClass().getResource(getFileName());
    }

    private Image getImageFront() {
        if (imageFront == null) {
            imageFront = loadImage();
        }
        return imageFront;
    }
    private Image getImageBack(){
        if(imageBack == null) {
            imageBack = loadImage();
        }
        return imageBack;
    }

    public void flipCard(){
        covered.set(!isCovered());
    }

    private boolean isCovered() {
        return covered.get();
    }
}
