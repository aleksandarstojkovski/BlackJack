package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.exception.InvalidDecksContainerSizeException;
import ch.supsi.blackjack.model.state.game.GameState;
import ch.supsi.blackjack.model.state.game.InitState;
import ch.supsi.blackjack.model.state.game.RoundState;

public class GameModel extends AbstractModel implements GameHandler {

    // singleton
    private static GameModel instance;
    // game state machine
    private GameState gameState;
    private final GameState initState;
    private final GameState roundState;
    private RoundHandler round;
    public static final int PLAYER_INITIAL_COINS = 1000;
    public static final int DEALER_INITIAL_COINS = 1000000;

    private GameModel() {
        super();
        initState = new InitState(this);
        roundState= new RoundState(this);
        setGameState(initState);
    }

    public RoundHandler getRound(){
        return round;
    }

    public void setGameState(GameState newGameState) {
        this.gameState = newGameState;
        System.out.println("Current state: " + newGameState.getClass().toString());
    }

    public void startGame(){
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
        try {
            newGame("Player 1","Dealer", DecksContainer.DEFAULT_NUMBER_OF_DECKS);
        } catch (InvalidDecksContainerSizeException e) {
            e.printStackTrace();
        }
    }

    public void newGame(String playerNickname,String dealerNickname, int numberOfDecks) throws InvalidDecksContainerSizeException {
        DecksContainer decksContainer = new DecksContainer(numberOfDecks);
        Player humanPlayer = new Player(playerNickname, PLAYER_INITIAL_COINS);
        Dealer dealer = new Dealer(dealerNickname, DEALER_INITIAL_COINS);
        round = new Round(this, humanPlayer, dealer,decksContainer);
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
