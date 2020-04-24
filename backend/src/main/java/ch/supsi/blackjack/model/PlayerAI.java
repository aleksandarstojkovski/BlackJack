package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.state.GameStateManager;

public class PlayerAI implements AI {
    protected int aiType = 0;
    private final Player aiEntity;

    PlayerAI(Player player) {
        this.aiEntity = player;
    }

    public void setAiType(int aiType) {
        this.aiType = aiType;
    }

    @Override
    public void compute(GameStateManager model) {
        if (aiType == 1) {
            while (aiEntity.getHandValue() < 16) {
                model.hit(aiEntity);
            }
        } else if (aiType == 2){
            while (aiEntity.getHandValue() < 18) {
                model.hit(aiEntity);
            }
        } else {
            while (aiEntity.getHandValue() < 17) {
                model.hit(aiEntity);
            }
        }
    }

    public void bet(Model model){
        int currentCoins=aiEntity.getCoins();
    }
}

