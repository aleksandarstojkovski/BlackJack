package ch.supsi.blackjack.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoundResultTest {

    @Test
    void testRoundResultLOOSE(){
        assertNotNull(RoundResult.LOOSE);
    }

    @Test
    void testRoundResultWIN(){
        assertNotNull(RoundResult.WIN);
    }

    @Test
    void testRoundResultDRAW(){
        assertNotNull(RoundResult.DRAW);
    }

    @Test
    void testRoundResultLength(){
        assertEquals(RoundResult.values().length,3);
    }

}
