package ch.supsi.blackjack.model;

public class DealerAI implements AI {

    private final Dealer aiEntity;

    DealerAI(Dealer dealer){
        this.aiEntity = dealer;
    }

    @Override
    public void compute(RoundHandler model) {
        while(model.getHand(aiEntity).value() < 17){
            model.hit(aiEntity);
        }
    }

}
