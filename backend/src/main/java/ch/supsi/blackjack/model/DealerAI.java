package ch.supsi.blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class DealerAI implements AI {
    private Dealer aiEntity;
    DealerAI(Dealer dealer){
        this.aiEntity = dealer;
    }

    @Override
    public List<Card> compute() {
        List<Card> pickedCards = new ArrayList<Card>();
        int currentHandValue = aiEntity.handValue();
        while(currentHandValue<17){
            Card currentCard = aiEntity.giveCard();
            pickedCards.add(currentCard);
            currentHandValue = currentHandValue + currentCard.getValue().cardValue;
        }
        return pickedCards;
    }
}
