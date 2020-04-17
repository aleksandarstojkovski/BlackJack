package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Model;

public class PlayerDealsState implements GameState {

    // singleton
    private static final PlayerDealsState instance = new PlayerDealsState();

    private PlayerDealsState() {
    }

    public static PlayerDealsState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    // business logic and state transition
    @Override
    public void updateState(Model model) {
        if (model.isPlayerStand()){
            //Order is important non scambiare l'ordine
            model.setCurrentState(DealerDealsState.instance());
            model.computeDealer();
        } else {
            int playerValue = model.getPlayerHandValue();
            if (playerValue < 21){
                // stay in this state until the user busted or stands or makes blackjack
                model.setCurrentState(PlayerDealsState.instance());
            } else if (playerValue == 21) {
                model.setCurrentState(TwentyOneState.instance());
                model.nextState();
            } else {
                model.setCurrentState(PlayerBustState.instance());
                model.nextState();
            }
        }
    }
}