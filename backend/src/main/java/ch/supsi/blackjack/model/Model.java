package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.state.GameStateManager;

import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    // singleton
    private static Model instance;
    private GameStateManager round;
    private Dealer dealer;
    private Player humanPlayer;
    private List<Player> aiPlayers;

    private final Coin[] coins = {
        new Coin(100),
        new Coin(200),
        new Coin(300),
        new Coin(400),
        new Coin(500)
    };

    private Model() {
        super();
    }

    // singleton
    public static Model instance() {
        if (instance == null) {
            instance = new Model();
        }

        return instance;
    }


    @Override
    public void newGame() {
        humanPlayer = new Player("Player 1",0);
        dealer = new Dealer();
        aiPlayers = new ArrayList<>();

        round = new Round(pcs, humanPlayer, dealer, aiPlayers);
        round.startGame();
    }

    @Override
    public void exitGame() {
        round.exitGame();
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
