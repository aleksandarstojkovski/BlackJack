package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Model;

public class SetupTableState implements GameState {

        // singleton
        private static SetupTableState instance = new SetupTableState();

        private SetupTableState() {
        }

        public static SetupTableState instance() {
            System.out.println("Current state: " +instance.getClass().toString());
            return instance;
        }

        // business logic and state transition
        @Override
        public void updateState(Model model) {
            if (model.getPlayerList().get(0).getHand().value() == 21){
                model.setCurrentState(BlackJackState.instance());
                model.nextState();
            } else {
                model.setCurrentState(PlayerDealsState.instance());
            }
        }

}
