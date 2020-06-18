package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.state.game.GameState;
import ch.supsi.blackjack.model.state.game.InitState;
import ch.supsi.blackjack.model.state.game.RoundState;

public class GameModel extends AbstractModel implements GameHandler {

    // singleton
    private static GameModel instance;
    // game state machine
    GameState gameState;
    private final GameState initState;
    private final GameState roundState;
    private RoundHandler round;

    private GameModel() {
        super();
        initState = new InitState(this);
        roundState= new RoundState(this);
        setGameState(initState);
    }
    GameModel(InitState initState,RoundState roundState,RoundHandler round) {
        super();
        this.initState = initState;
        this.roundState= roundState;
        this.round = round;
        setGameState(initState);
    }

    public RoundHandler getRound(){
        return round;
    }

    public void setGameState(GameState newGameState) {
        this.gameState = newGameState;
        System.out.println("Current state: " + newGameState.getClass().toString());
    }

    private void startGame(){
        gameState.startGame();
    }

    public void exitGame(){
        gameState.exitGame();
    }

    public GameState getInitState() {
        return initState;
    }

    public GameState getRoundState() {
        return roundState;
    }

    // singleton
    public static GameModel instance() {
        if (instance == null) {
            instance = new GameModel();
        }
        return instance;
    }

    @Override
    public void newGame(){
        round = new RoundMediator(this, "Player1", "Dealer1");
        startGame();
    }

    @Override
    public void exitRound() {
        round.exitRound();
    }

    @Override
    public void nextRound() {
        round.nextRound();
    }

    @Override
    public void hit() {
        round.playerHit();
    }

    @Override
    public void stand() {
        round.setPlayerStand();
    }

    @Override
    public void bet(int amount) {
        round.playerBet(amount);
    }

    @Override
    public void confirmBet() {
        round.playerConfirmBet();
    }

}
