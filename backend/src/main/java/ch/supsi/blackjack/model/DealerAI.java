package ch.supsi.blackjack.model;

public class DealerAI implements AI {

    private final Dealer aiEntity;

    DealerAI(Dealer dealer){
        this.aiEntity = dealer;
    }

    @Override
    public void compute(RoundHandler model) {
        while(aiEntity.getHandValue() < 17){
            model.hit(aiEntity);
        }
    }

}
