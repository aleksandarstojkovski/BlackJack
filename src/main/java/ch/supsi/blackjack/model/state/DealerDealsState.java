package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Model;

public class DealerDealsState implements GameState {

    //Singleton
    private static DealerDealsState instance = new DealerDealsState();

    private DealerDealsState() {}

    public static DealerDealsState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    @Override
    public void updateState(Model model) {
        if(model.getDealer().getHand().value()>21){
            model.setCurrentState(DealerBurstState.instance());
        } else {

        }
    }
}
