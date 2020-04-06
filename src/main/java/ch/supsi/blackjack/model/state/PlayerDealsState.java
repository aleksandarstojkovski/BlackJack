package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Dealer;
import ch.supsi.blackjack.model.Model;

//ToDo: Creare
public class PlayerDealsState implements GameState {


    //Singleton
    private static PlayerDealsState instance = new PlayerDealsState();

    private PlayerDealsState() {
    }

    public static PlayerDealsState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    //Business logic and state transition
    @Override
    public void updateState(Model model) {
        if (model.playerStandProperty().get()){
            model.setCurrentState(DealerDealsState.instance());
        }
        if (model.getPlayerList().get(0).getHand().value()<21){
            // stay in this state until the user bursts or stands or makes blackjack
            model.setCurrentState(PlayerDealsState.instance());
        } else {
            model.playerBurstedProperty().set(true);
            model.setCurrentState(PlayerBurstState.instance());
        }
    }
}