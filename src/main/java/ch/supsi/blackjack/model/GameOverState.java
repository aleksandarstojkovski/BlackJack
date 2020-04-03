package ch.supsi.blackjack.model;
//ToDo: Creare
public class GameOverState implements GameState {


    //Singleton
    private static GameOverState instance = new GameOverState();

    private GameOverState() {
    }

    public static GameOverState instance() {
        return instance;
    }

    //Business logic and state transition
    @Override
    public void updateState(Model model) {
        System.out.println("È tempo di puntare");
        model.setCurrentState(DealOnState.instance());
    }
}