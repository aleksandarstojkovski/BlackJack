package ch.supsi.blackjack.event;

import ch.supsi.blackjack.model.Hand;

public class DealerHandUpdateEvent extends AbstractEvent {

        private final int handValue;
        private final int lastCardValue;
        private final int handSize;
        public DealerHandUpdateEvent(Object source, Hand hand) {
            super(source);
            this.handValue = hand.value();
            this.handSize = hand.getCardList().size();
            this.lastCardValue = hand.getCardList().get(handSize-1).getValue().cardValue;
        }

        public int getValue() {
            return handValue;
        }
        public int getHandSize(){
            return handSize;
        }
        public int getLastCardValue(){
            return lastCardValue;
        }

}
