package ch.supsi.blackjack.model;
//ToDo: Creare
public class PlayerState implements GameState {


    //Singleton
    private static PlayerState instance = new PlayerState();

    private PlayerState() {
    }

    public static PlayerState instance() {
        return instance;
    }

    //Business logic and state transition
    @Override
    public void updateState(Model model) {
        if (model.getPlayerList().get(0).getHand().value()>21){
            System.out.println("Il Player ha sballato");
            model.setCurrentState(DealerState.instance());
        } else {
            System.out.println("Tocca al Dealer");
            model.setCurrentState(DealerState.instance());
        }
    }
}