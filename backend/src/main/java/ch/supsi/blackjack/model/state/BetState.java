package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Model;

public class BetState implements GameState {

    // singleton
    private static BetState instance = new BetState();

    private BetState() {
    }

    public static BetState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    // business logic and state transition
    @Override
    public void updateState(Model model) {
        // at least one bet - no confirmation yet
        if (model.getAtLeastOneCoinBet().get()){
            model.betsOpenProperty().set(true);
        }
        // bet confirmed
        if (model.betConfirmedProperty().get()){
            model.openRound();
            model.betsOpenProperty().setValue(false);
            model.dealsOpenProperty().setValue(true);
            model.setCurrentState(SetupTableState.instance());
        }
    }

}