package ch.supsi.blackjack.model.state.game;

import ch.supsi.blackjack.model.GameModel;

public class RoundState implements GameState{

    private final GameModel gameModel;

    public RoundState(GameModel gameModel){
        this.gameModel = gameModel;
    }

    @Override
    public void startGame() {
        System.out.println("Partita già avviata");
    }

    @Override
    public void exitGame() {
        gameModel.setGameState(gameModel.getInitState());
    }
}
