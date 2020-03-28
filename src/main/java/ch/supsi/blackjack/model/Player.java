package ch.supsi.blackjack.model;

public class Player {
    //ToDo: inserire un boolean per gestire se il player è gestito dall'AI.
    /*
    private String name;
    private String surname;
    private String nickname;
     */
    private Hand playerHand;

    public Player() {
        this.playerHand = new Hand();
    }

    private void addCardToHand (Card newCard){
        this.playerHand.addCard(newCard);
    }

    public Hand getPlayerHand() {
        return this.playerHand;
    }
}
