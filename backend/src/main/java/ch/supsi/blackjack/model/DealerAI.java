package ch.supsi.blackjack.model;

public class DealerAI implements AI {
    private Dealer aiEntity;
    DealerAI(Dealer dealer){
        this.aiEntity = dealer;
    }

    @Override
    public void compute(Model model) {
        while(aiEntity.handValue()<17){
            model.hitInternal(aiEntity);
        }
    }
}
