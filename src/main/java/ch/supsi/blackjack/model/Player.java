package ch.supsi.blackjack.model;

public class Player {
    //ToDo: inserire un boolean per gestire se il player è gestito dall'AI.
    /*
    private String name;
    private String surname;
    */
    private String nickname;
    private Hand hand;

    Player(String nickname) {
        this.nickname = nickname;
        this.hand = new Hand();
    }

    private void addCardToHand (Card newCard){
        this.hand.addCard(newCard);
    }

    public Hand getHand() {
        return this.hand;
    }
}
