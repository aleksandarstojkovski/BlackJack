package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.state.GameStateManager;

import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    // singleton
    private static Model instance;
    private GameStateManager round;

    private final Coin[] coins = new Coin[Coin.Values.length];

    private Model() {
        super();
        initCoins();
    }

    private void initCoins() {
        List<Coin> list = new ArrayList<>();
        for (int v : Coin.Values)
            list.add(new Coin(v));

        list.toArray(coins);
    }

    // singleton
    public static Model instance() {
        if (instance == null) {
            instance = new Model();
        }

        return instance;
    }


    @Override
    public void newGame(String nickName) {
        Player humanPlayer = new Player(nickName, 0);
        Dealer dealer = new Dealer();
        List<Player> aiPlayers = new ArrayList<>();

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
