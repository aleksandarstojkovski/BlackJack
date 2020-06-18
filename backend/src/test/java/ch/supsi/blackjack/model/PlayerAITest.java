package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.state.round.BetState;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class PlayerAITest {

    @Test
    void testConstructor(){
        PlayerAI pai = new PlayerAI(new Player("player",100), PlayerAI.AIType.RISKY);
        assertNotNull(pai);
    }

    @Test
    void compute(){
        RoundHandler round = Mockito.mock(RoundMediator.class);
        Hand hand = new Hand();
        Card.Builder builder = new Card.Builder();
        hand.addCard(builder.setSeed(Seed.D).setValue(Value.TEN).build());
        hand.addCard(builder.setSeed(Seed.D).setValue(Value.TEN).build());
        when(round.getHand(Mockito.anyObject())).thenReturn(hand);
        PlayerAI pai = new PlayerAI(new Player("player",100), PlayerAI.AIType.PRUDENT);
        pai.compute(round);
        verify(round, Mockito.times(1)).getHand(Mockito.any(Player.class));
    }

}
