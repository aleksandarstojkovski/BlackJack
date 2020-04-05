package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Model;

//ToDo: Creare
public class DealerBurstState implements GameState {


    //Singleton
    private static DealerBurstState instance = new DealerBurstState();

    private DealerBurstState() {
    }

    public static DealerBurstState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    //Business logic and state transition
    @Override
    public void updateState(Model model) {
        System.out.println("Vittoria del Player");
        model.setCurrentState(GameOverState.instance());
    }
}