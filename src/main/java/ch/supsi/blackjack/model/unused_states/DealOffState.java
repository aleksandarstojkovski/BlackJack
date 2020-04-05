package ch.supsi.blackjack.model.unused_states;

import ch.supsi.blackjack.model.Model;
import ch.supsi.blackjack.model.state.GameOverState;
import ch.supsi.blackjack.model.state.GameState;

//ToDo: Creare
public class DealOffState implements GameState {


    //Singleton
    private static DealOffState instance = new DealOffState();

    private DealOffState() {
    }

    public static DealOffState instance() {
        return instance;
    }


    @Override
    public void updateState(Model model) {
        int delaerValue = model.getDealer().getHand().value();
        int PlayerValue = model.getPlayerList().get(0).getHand().value();
        if (delaerValue>PlayerValue){
            System.out.println("Vince il banco");
        } else if (delaerValue<PlayerValue){
            System.out.println("Vince il Player");
        } else {
            System.out.println("Pareggio");
        }
        model.setCurrentState(GameOverState.instance());
    }
}