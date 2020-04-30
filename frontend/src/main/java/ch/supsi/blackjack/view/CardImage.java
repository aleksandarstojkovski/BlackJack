package ch.supsi.blackjack.view;

import ch.supsi.blackjack.model.Card;
import javafx.scene.image.Image;

import java.net.URL;

public class CardImage implements Drawable {
    private final Card card;
    private Image imageFront;
    private Image imageBack;
    private boolean covered;

    public CardImage(Card card) {
        this.card = card;
        covered = false;
    }
    public CardImage(Card card, boolean covered) {
        this.card = card;
        this.covered = covered;
    }


    private String getFileName() {
        if(covered){
            return String.format("/ch/supsi/blackjack/images/cards/back_%s.png", card.getBack());
        }else{
            return String.format("/ch/supsi/blackjack/images/cards/%s_of_%s.png", card.getValue().label, card.getSeed().label);
        }
    }

    @Override
    public Image getImage(){
       if(covered){
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
        covered = !covered;
    }

}
