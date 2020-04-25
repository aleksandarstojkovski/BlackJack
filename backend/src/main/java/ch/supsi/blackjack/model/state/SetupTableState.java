package ch.supsi.blackjack.model.state;

public class SetupTableState implements GameState {

        // singleton
        private static final SetupTableState instance = new SetupTableState();

        private SetupTableState() {
        }

        public static SetupTableState instance() {
            System.out.println("Current state: " +instance.getClass().toString());
            return instance;
        }

        // business logic and state transition
        @Override
        public void updateState(GameStateManager model) {
            if (model.getPlayerHandValue() == model.BLACKJACK){
                model.setCurrentState(BlackJackState.instance());
                model.goNextState();
            } else {
                model.setCurrentState(PlayerDealsState.instance());
            }
        }

}
