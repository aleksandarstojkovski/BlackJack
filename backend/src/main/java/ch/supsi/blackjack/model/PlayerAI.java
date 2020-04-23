package ch.supsi.blackjack.model;

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
    public void compute(Model model) {
        if (aiType == 1) {
            while (aiEntity.getHandValue() < 16) {
                model.hitInternal(aiEntity);
            }
        } else if (aiType == 2){
            while (aiEntity.getHandValue() < 18) {
                model.hitInternal(aiEntity);
            }
        } else {
            while (aiEntity.getHandValue() < 17) {
                model.hitInternal(aiEntity);
            }
        }
    }

    public void bet(Model model){
        int currentCoins=aiEntity.getCoins();
    }
}

