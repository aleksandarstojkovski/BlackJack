package ch.supsi.blackjack.model.state;

public class DealerDealsState implements GameState {

    // singleton
    private static final DealerDealsState instance = new DealerDealsState();

    private DealerDealsState() {}

    public static DealerDealsState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    // business logic and state transition
    @Override
    public void updateState(GameStateManager model) {
//        model.nextRoundProperty().setValue(true);
        if(model.getDealerHandValue() > 21){
            model.setCurrentState(DealerBustState.instance());
        } else {
            model.setCurrentState(UpdateTableState.instance());
        }
        model.goNextState();
    }
}
