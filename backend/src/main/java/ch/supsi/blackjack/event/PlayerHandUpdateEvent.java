package ch.supsi.blackjack.event;

public class PlayerHandUpdateEvent extends AbstractEvent {

        private int handValue;

        public PlayerHandUpdateEvent(Object source, int handValue) {
            super(source);
            this.handValue = handValue;
        }

        public int getValue() {
            return handValue;
        }

}
