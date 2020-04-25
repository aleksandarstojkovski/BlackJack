package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.state.GameStateManager;

public class PlayerAI implements AI {
    public enum AIType { PRUDENT, MEDIUM, RISKY }

    protected final AIType aiType;
    private final Player aiEntity;

    PlayerAI(Player player, AIType aiType) {
        this.aiEntity = player;
        this.aiType = aiType;
    }

    @Override
    public void compute(GameStateManager model) {
        int limit;
        switch (aiType) {
            case RISKY:
                limit = 18;
                break;
            case MEDIUM:
                limit = 17;
                break;
            case PRUDENT:
            default:
                limit = 16;
                break;
        }
        while (aiEntity.getHandValue() < limit) {
            model.hit(aiEntity);
        }
    }

    public void bet(Model model){
        int currentCoins=aiEntity.getCoins();
    }
}

