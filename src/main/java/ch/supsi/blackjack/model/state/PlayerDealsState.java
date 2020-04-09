package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Model;

public class PlayerDealsState implements GameState {

    // singleton
    private static PlayerDealsState instance = new PlayerDealsState();

    private PlayerDealsState() {
    }

    public static PlayerDealsState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    // business logic and state transition
    @Override
    public void updateState(Model model) {
        if (model.playerStandProperty().get()){
            //Order is important non scambiare l'ordine
            model.setCurrentState(DealerDealsState.instance());
            model.compute(0);
        } else {
            if (model.getPlayerList().get(0).getHand().value()<=21){
                // stay in this state until the user bursts or stands or makes blackjack
                model.setCurrentState(PlayerDealsState.instance());
            } else {
                model.playerBurstProperty().set(true);
                model.setCurrentState(PlayerBurstState.instance());
            }
        }
    }
}