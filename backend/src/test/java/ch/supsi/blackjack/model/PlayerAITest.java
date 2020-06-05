package ch.supsi.blackjack.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlayerAITest {

    @Test
    void testConstructor(){
        PlayerAI pai = new PlayerAI(new Player("player",100), PlayerAI.AIType.RISKY);
        assertNotNull(pai);
    }

}
