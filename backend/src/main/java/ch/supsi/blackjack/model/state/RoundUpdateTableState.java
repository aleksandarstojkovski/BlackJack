package ch.supsi.blackjack.model.state;

/**
 * The Round is completed
 * If player has more money he can still play
 * Otherwise the game is over
 * Next State: BetState,GameOverState
 */
public class RoundUpdateTableState implements RoundState {

    // singleton
    private static final RoundUpdateTableState instance = new RoundUpdateTableState();

    public RoundUpdateTableState() {
        printStatus();
    }

    @Override
    public void next(GameStateManager round) {
        round.setState(new RoundBetState());
    }

    @Override
    public void prev(GameStateManager round) {

    }

    @Override
    public void printStatus() {
        System.out.println("Current state: " + this.getClass().toString());
    }

    // business logic and state transition
    @Override
    public void updateState(GameStateManager round) {
        round.setRoundCompleted();

        if (round.isPlayerWithMoney()){
            next(round);
        } else {
            round.setGameOver();
            round.setState(new RoundGameOverState());
            round.goNextState();
        }

    }

}