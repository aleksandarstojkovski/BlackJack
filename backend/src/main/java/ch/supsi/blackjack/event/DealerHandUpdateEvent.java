package ch.supsi.blackjack.event;

public class DealerHandUpdateEvent extends AbstractEvent {

        private int handValue;

        public DealerHandUpdateEvent(Object source, int handValue) {
            super(source);
            this.handValue = handValue;
        }

        public int getValue() {
            return handValue;
        }

}
