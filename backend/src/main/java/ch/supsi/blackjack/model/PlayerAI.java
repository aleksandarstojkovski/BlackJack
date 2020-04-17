package ch.supsi.blackjack.model;

public class PlayerAI implements AI {
    protected int aiType=0;
    private final Player aiEntity;

    PlayerAI(Player player){
        this.aiEntity=player;
    }

    public void setAiType(int aiType) {
        this.aiType = aiType;
    }

    @Override
    public void compute(Model model) {

    }
}
