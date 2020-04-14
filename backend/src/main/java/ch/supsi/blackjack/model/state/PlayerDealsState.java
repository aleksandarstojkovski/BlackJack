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
            model.compute(model.getDealer());
        } else {
            int playerValue = model.getPlayerList().get(0).getHand().value();
            if (playerValue<21){
                // stay in this state until the user busted or stands or makes blackjack
                model.setCurrentState(PlayerDealsState.instance());
            } else if (playerValue == 21) {
                model.setCurrentState(TwenrtyOneState.instance());
                model.nextState();
            } else {
                model.setCurrentState(PlayerBustState.instance());
                model.nextState();
            }
        }
    }
}