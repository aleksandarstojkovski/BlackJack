package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Model;
import ch.supsi.blackjack.model.Player;
import javafx.scene.control.Alert;

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

        // TODO: temp dialog
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        int delaerValue = model.getDealer().getHand().value();
        int playerValue = model.getPlayerList().get(0).getHand().value();

        if (model.playerBurstProperty().get()){
            alert.setTitle("Bust");
            alert.setHeaderText("You busted.");
            // dealer wins
            for (Player p : model.getPlayerList() ){
                p.getHand().takeBets();
            }
        }

        if (model.dealerBurstProperty().get()){
            alert.setTitle("Win");
            alert.setHeaderText("You win.");
            // player wins
            int bettedCoins;
            for (Player p : model.getPlayerList()){
                bettedCoins=p.getHand().takeBets();
                p.giveCoins(bettedCoins*2);
            }
        }

        // no-one busted
        if (delaerValue > playerValue){
            alert.setTitle("Lose");
            alert.setHeaderText("You lose.");
            // dealer wins
            for (Player p : model.getPlayerList() ){
                p.getHand().takeBets();
            }
        } else if (delaerValue < playerValue) {
            alert.setTitle("Win");
            alert.setHeaderText("You win.");
            // player wins
            int bettedCoins;
            for (Player p : model.getPlayerList()){
                bettedCoins=p.getHand().takeBets();
                p.giveCoins(bettedCoins*2);
            }
        } else {
            alert.setTitle("Draw");
            alert.setHeaderText("Draw.");
            int bettedCoins;
            for (Player p : model.getPlayerList()){
                bettedCoins=p.getHand().takeBets();
                p.giveCoins(bettedCoins);
            }
        }

        // removes bets from the dealer
        model.getDealer().getHand().takeBets();

        model.dealsOpenProperty().set(false);
        model.betsOpenProperty().set(false);
        model.playerBurstProperty().set(false);
        model.betConfirmedProperty().set(false);
        model.getAtLeastOneCoinBet().set(false);
        model.playerStandProperty().set(false);
        model.dealerBurstProperty().set(false);

        alert.showAndWait();

        if (model.getPlayerList().get(0).getCoins()!=0){
            model.setCurrentState(BetState.instance());
        } else {
            model.nextRoundProperty().set(false);
            model.setCurrentState(GameOverState.instance());
            model.nextState();
        }

    }

}