package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.state.GameStateManager;

public class VirtualPlayer extends Player implements AI {
    protected AI ai;
    public VirtualPlayer(String nickname, int playerID) {
        super(nickname, playerID);

        this.ai = new PlayerAI(this, PlayerAI.AIType.RISKY);
    }

    @Override
    public void compute(GameStateManager model) {
        ai.compute(model);
    }
}
