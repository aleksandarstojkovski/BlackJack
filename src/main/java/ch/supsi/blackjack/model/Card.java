package ch.supsi.blackjack.model;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Objects;

public class Card {

    private Image image;
    private final Seed seed;
    private final Value value;

    Card(Seed seed, Value value){
        this.seed = seed;
        this.value = value;
    }

    public Seed getSeed() {
        return seed;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Card{" +
                "seed=" + seed.label +
                ", value=" + value.label +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return seed == card.seed &&
                value == card.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seed, value);
    }

    public String getFileName() {
        return String.format("/ch/supsi/blackjack/images/cards/%s_of_%s.png", value.label, seed.label);
    }

    public Image getImage() {
        if(image == null) {
            URL url = this.getClass().getResource(getFileName());
            image = new Image(url.toString());
        }
        return image;
    }

}
