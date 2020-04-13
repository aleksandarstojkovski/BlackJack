package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Model;
import ch.supsi.blackjack.model.Player;

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

        model.dealsOpenProperty().set(false);
        model.betsOpenProperty().set(false);
        model.playerBurstProperty().set(false);
        model.betConfirmedProperty().set(false);
        model.getAtLeastOneCoinBet().set(false);
        model.playerStandProperty().set(false);

        System.out.println("here");

        int delaerValue = model.getDealer().getHand().value();
        int playerValue = model.getPlayerList().get(0).getHand().value();

        if (delaerValue > playerValue && delaerValue <= 21){
            System.out.println("dealer wins!!!!!");
            // dealer wins
            for (Player p : model.getPlayerList() ){
                p.getHand().takeBets();
            }
        } else if (delaerValue < playerValue && playerValue <= 21 || delaerValue > 21){
            System.out.println("player wins!!!!!");
            // player wins
            int bettedCoins;
            for (Player p : model.getPlayerList()){
                bettedCoins=p.getHand().takeBets();
                p.giveCoins(bettedCoins*2);
            }
        } else {
            System.out.println("draw !!!!!");
            int bettedCoins;
            for (Player p : model.getPlayerList()){
                bettedCoins=p.getHand().takeBets();
                p.giveCoins(bettedCoins);
            }
        }

        model.getDealer().getHand().takeBets();

        if (model.getPlayerList().get(0).getCoins()!=0){
            model.setCurrentState(BetState.instance());
        } else {
            model.setCurrentState(GameOverState.instance());
            model.nextState();
        }

    }

}