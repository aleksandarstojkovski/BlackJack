package ch.supsi.blackjack.model;
//ToDo: Creare
public class PlayerBurstState implements GameState {


    //Singleton
    private static PlayerBurstState instance = new PlayerBurstState();

    private PlayerBurstState() {
    }

    public static PlayerBurstState instance() {
        return instance;
    }

    //Business logic and state transition
    @Override
    public void updateState(Model model) {
        System.out.println("Vittoria del banco");
        model.setCurrentState(GameOverState.instance());
    }
}