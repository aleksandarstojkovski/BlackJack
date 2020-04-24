package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.state.GameStateManager;

public class DealerAI implements AI {
    private final Dealer aiEntity;
    DealerAI(Dealer dealer){
        this.aiEntity = dealer;
    }

    @Override
    public void compute(GameStateManager model) {
        while(aiEntity.getHandValue() < 17){
            model.hit(aiEntity);
        }
    }
}
