package ch.supsi.blackjack.model;

import java.util.List;

public class PlayerAI implements AI {
    protected int aiType=0;
    private Player aiEntity;

    PlayerAI(Player player){
        this.aiEntity=player;
    }

    public void setAiType(int aiType) {
        this.aiType = aiType;
    }

    @Override
    public List<Card> compute() {
    return null;
    }
}
