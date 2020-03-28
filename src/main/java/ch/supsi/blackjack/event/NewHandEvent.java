package ch.supsi.blackjack.event;

import ch.supsi.blackjack.model.Hand;

public class NewHandEvent extends AbstractEvent {
        private Hand hand;

        public NewHandEvent(Object source, Hand hand) {
            super(source);
            this.hand = hand;
        }
        public Hand getHand() {
            return hand;
        }

}
