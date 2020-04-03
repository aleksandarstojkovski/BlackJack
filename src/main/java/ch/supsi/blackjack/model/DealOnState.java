package ch.supsi.blackjack.model;
//ToDo: Creare
public class DealOnState implements GameState {


    //Singleton
    private static DealOnState instance = new DealOnState();

    private DealOnState() {
    }

    public static DealOnState instance() {
        return instance;
    }

    //Business logic and state transition
    @Override
    public void updateState(Model model) {
        System.out.println("Puntata finita");
        System.out.println("Tocca al Player");
        model.setCurrentState(PlayerState.instance());
    }
}