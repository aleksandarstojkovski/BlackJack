package ch.supsi.blackjack.model;
//ToDo: Creare
public class InitState implements GameState {


    //Singleton
    private static InitState instance = new InitState();

    private InitState() {
    }

    public static InitState instance() {
        return instance;
    }

    //Business logic and state transition
    @Override
    public void updateState(Model model) {
        System.out.println("È tempo di puntare");
        model.setCurrentState(DealOnState.instance());
    }
}