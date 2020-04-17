package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Model;

public class DealerBustState implements GameState {


    // singleton
    private static DealerBustState instance = new DealerBustState();

    private DealerBustState() {
    }

    public static DealerBustState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    // business logic and state transition
    @Override
    public void updateState(Model model) {
        // dealer bust property
        model.setDealerBusted();
        model.setCurrentState(UpdateTableState.instance());
        model.nextState();
    }

}