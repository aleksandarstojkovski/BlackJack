package ch.supsi.blackjack.model;

public class Dealer extends Player{
    private DecksContainer decksContainer;
    public Dealer() {
        super("Delaer");
        try {
            decksContainer = new DecksContainer(3);
            // ToDo: rendere modificabile il numero di mazzi
        } catch (InvalidDecksContainerSizeException e) {
            e.printStackTrace();
        }
        decksContainer.shuffle();
    }
    public Card giveCard(){
        return decksContainer.getCard();
    }

}
