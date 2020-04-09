package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Model;
import ch.supsi.blackjack.model.Player;

public class DealerDealsState implements GameState {

    // singleton
    private static DealerDealsState instance = new DealerDealsState();

    private DealerDealsState() {}

    public static DealerDealsState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    // business logic and state transition
    @Override
    public void updateState(Model model) {
        if(model.getDealer().getHand().value()>21){
            model.setCurrentState(DealerBurstState.instance());
        } else {
            model.setCurrentState(UpdateTableState.instance());
        }
    }
}
