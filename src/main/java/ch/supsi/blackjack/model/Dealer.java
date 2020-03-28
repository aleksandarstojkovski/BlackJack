package ch.supsi.blackjack.model;

public class Dealer {
    private DecksContainer decksContainer;
    private Hand dealerHand;
    public Dealer() {
        try {
            decksContainer = new DecksContainer(3);
            // ToDo: rendere modificabile il numero di mazzi
        } catch (InvalidDecksContainerSizeException e) {
            e.printStackTrace();
        }
        decksContainer.shuffle();
        dealerHand = new Hand();
    }
    public Card giveCard(){
        return decksContainer.getCard();
    }

}
