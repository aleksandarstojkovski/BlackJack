package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.state.GameStateManager;

import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    // singleton
    private static Model instance;

    private GameStateManager round;
    private Dealer dealer;
    private List<Player> playerList;

    private final Coin[] coins = {new Coin(100),new Coin(200),new Coin(300), new Coin(400),new Coin(500)};

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
        this.dealer = new Dealer();
        this.playerList = new ArrayList<>();
        playerList.add(new Player("Player 1",0));
        round = new Round(pcs, dealer, playerList);
        round.start();
    }

    @Override
    public void exitGame() {
        round.exit();
    }

    @Override
    public void nextRound() {
        round.next();
    }

    @Override
    public void hit() {
        round.playerHit();
    }

    @Override
    public void stand() {
        round.playerStand();
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
