package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.exception.InvalidDecksContainerSizeException;
import ch.supsi.blackjack.model.state.game.InitState;
import ch.supsi.blackjack.model.state.game.RoundState;
import ch.supsi.blackjack.model.state.game.GameState;

import java.util.ArrayList;
import java.util.List;

public class GameModel extends AbstractModel implements GameHandler {

    // singleton
    private static GameModel instance;
    // Macchina a stati di Game
    private GameState gameState;
    private final GameState initState;
    private final GameState roundState;

    private RoundHandler round;

    private final Coin[] coins = new Coin[Coin.Values.length];

    private GameModel() {
        super();

        initState = new InitState(this);
        roundState= new RoundState(this);
        setGameState(initState);
        initCoins();
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

    private void initCoins() {
        List<Coin> list = new ArrayList<>();
        for (int v : Coin.Values)
            list.add(new Coin(v));

        list.toArray(coins);
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
            newGame("Player 1", DecksContainer.DEFAULT_NUMBER_OF_DECKS);
        } catch (InvalidDecksContainerSizeException e) {
            e.printStackTrace();
        }
    }
    public void newGame(String nickName, int numberOfDecks) throws InvalidDecksContainerSizeException {
        DecksContainer decksContainer = new DecksContainer(numberOfDecks);
        Player humanPlayer = new Player(nickName);
        Dealer dealer = new Dealer(decksContainer);
        round = new Round(this, humanPlayer, dealer);
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

    @Override
    public Coin[] getCoins() {
        return coins;
    }
}
