package ch.supsi.blackjack.model;

public class PlayerAI implements AI {

    public enum AIType { PRUDENT, MEDIUM, RISKY }
    protected final AIType aiType;
    private final Player aiEntity;

    PlayerAI(Player player, AIType aiType) {
        this.aiEntity = player;
        this.aiType = aiType;
    }

    @Override
    public void compute(RoundHandler model) {
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
        // TODO: not really nice
        while (model.getHand(aiEntity).value() < limit) {
            model.hit(aiEntity);
        }
    }

}

