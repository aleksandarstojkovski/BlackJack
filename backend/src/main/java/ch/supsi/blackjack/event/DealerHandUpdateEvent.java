package ch.supsi.blackjack.event;

import ch.supsi.blackjack.model.Hand;
import ch.supsi.blackjack.model.state.GameState;

public class DealerHandUpdateEvent extends AbstractEvent {

        private final int handValue;
        private final int lastCardValue;
        private final int handSize;
        private final GameState currentState;
        public DealerHandUpdateEvent(Object source, Hand hand, GameState currentState) {
            super(source);
            this.handValue = hand.value();
            this.handSize = hand.getCardList().size();
            this.lastCardValue = hand.getCardList().get(handSize-1).getValue().cardValue;
            this.currentState = currentState;
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
        public GameState getCurrentState(){
            return currentState;
        }

}
