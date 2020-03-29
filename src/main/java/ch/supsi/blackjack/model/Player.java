package ch.supsi.blackjack.model;

public class Player {
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

    public String getNickname() {
        return nickname;
    }
}

