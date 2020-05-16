package ch.supsi.blackjack.model.state.game;

import ch.supsi.blackjack.model.GameModel;
import ch.supsi.blackjack.model.RoundHandler;

public class InitState implements GameState{

    GameModel gameModel;

    public InitState(GameModel gameModel){
    this.gameModel = gameModel;
    }

    @Override
    public void startGame() {
        RoundHandler round = gameModel.getRound();
        gameModel.setGameState(gameModel.getRoundState());
        round.startRound();
    }

    @Override
    public void exitGame() {
        System.out.println("Gioco non in esecuzione, impossibile uscire");
    }

}
