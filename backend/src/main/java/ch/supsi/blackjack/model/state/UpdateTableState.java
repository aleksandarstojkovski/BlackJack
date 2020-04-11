package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Model;

public class UpdateTableState implements GameState {

    // singleton
    private static UpdateTableState instance = new UpdateTableState();

    private UpdateTableState() {
    }

    public static UpdateTableState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    // business logic and state transition
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