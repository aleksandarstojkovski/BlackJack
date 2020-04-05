package ch.supsi.blackjack.model.state;

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
        if (model.getPlayerList().get(0).getHand().value()>21){
            System.out.println("Il Player ha sballato");
            model.setCurrentState(DealerDealsState.instance());
        } else {
            System.out.println("Tocca al Dealer");
            model.setCurrentState(DealerDealsState.instance());
        }
    }
}