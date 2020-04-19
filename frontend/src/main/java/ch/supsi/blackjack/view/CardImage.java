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


    String getFileName() {
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
    private Image getImageFront() {
        if (imageFront == null) {
            URL url = this.getClass().getResource(getFileName());
            imageFront = new Image(url.toString());
        }
        return imageFront;
    }
    private Image getImageBack(){
        if(imageBack == null) {
            URL url =  this.getClass().getResource(getFileName());
            imageBack = new Image(url.toString());
        }
        return imageBack;
    }

    public void flipCard(){
        covered = !covered;
    }

}
