package ch.supsi.blackjack.model;

public class DealerState implements GameState {

    //Singleton
    private static DealerState instance = new DealerState();

    private DealerState() {}

    public static DealerState instance() {
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
