package ch.supsi.blackjack.model;

public class Dealer extends VirtualPlayer {
    private DecksContainer decksContainer;

    public Dealer(DecksContainer decksContainer) {
        super("Dealer");
        this.ai = new DealerAI(this);
        this.decksContainer = decksContainer;
        this.decksContainer.shuffle();
    }

    public Card giveCard(){
        return decksContainer.getCard();
    }
}
