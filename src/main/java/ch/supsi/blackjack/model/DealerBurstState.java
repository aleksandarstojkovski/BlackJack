package ch.supsi.blackjack.model;
//ToDo: Creare
public class DealerBurstState implements GameState {


    //Singleton
    private static DealerBurstState instance = new DealerBurstState();

    private DealerBurstState() {
    }

    public static DealerBurstState instance() {
        return instance;
    }

    //Business logic and state transition
    @Override
    public void updateState(Model model) {
        System.out.println("Vittoria del Player");
        model.setCurrentState(GameOverState.instance());
    }
}