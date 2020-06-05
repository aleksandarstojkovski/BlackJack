package ch.supsi.blackjack.command;

import ch.supsi.blackjack.model.GameHandler;
import ch.supsi.blackjack.model.MockGameModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BetCommandTest {
    private final int bet;
    private final GameHandler receiver;

    BetCommandTest() {
        this.bet = 100;
        this.receiver = MockGameModel.build();
    }

    @Test
    void execute() {
        assertNotNull(receiver);
        assertEquals(100,bet);
    }
}