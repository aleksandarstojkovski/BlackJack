package ch.supsi.blackjack.model.state;

/**
 * This is the turn of the Main Player.
 * He can hit or stand.
 * In case of Blackjack the Round is over, he won
 * In case of Bust the Round is over, dealer won
 */
public class PlayerDealsState implements GameState {

    // singleton
    private static final PlayerDealsState instance = new PlayerDealsState();

    private PlayerDealsState() {
    }

    public static PlayerDealsState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    // business logic and state transition
    @Override
    public void updateState(GameStateManager model) {
        if (model.isPlayerStand()){
            //Order of call is important
            model.setCurrentState(DealerDealsState.instance());
            model.updateDealer();
            model.computeDealer();
        } else {
            int playerValue = model.getPlayerHandValue();
            if (playerValue < model.BLACKJACK){
                // stay in this state until the user bust or stands or makes blackjack
                model.setCurrentState(PlayerDealsState.instance());
            } else if (playerValue == model.BLACKJACK) {
                model.setCurrentState(TwentyOneState.instance());
                model.updateDealer();
                model.goNextState();
            } else {
                model.setCurrentState(PlayerBustState.instance());
                model.updateDealer();
                model.goNextState();
            }
        }
    }
}