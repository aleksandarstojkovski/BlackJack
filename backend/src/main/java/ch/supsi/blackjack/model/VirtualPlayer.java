package ch.supsi.blackjack.model;

public class VirtualPlayer extends Player implements AI {

    protected AI ai;

    public VirtualPlayer(String nickname) {
        super(nickname);
        this.ai = new PlayerAI(this, PlayerAI.AIType.RISKY);
    }

    @Override
    public void compute(RoundHandler model) {
        ai.compute(model);
    }

}
