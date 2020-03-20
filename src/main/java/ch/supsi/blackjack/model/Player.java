package ch.supsi.blackjack.model;

public class Player {
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
