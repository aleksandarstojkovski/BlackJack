package ch.supsi.blackjack.command;

import ch.supsi.blackjack.model.GameHandler;
import ch.supsi.blackjack.model.GameModel;
import ch.supsi.blackjack.model.MockGameModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfirmBetCommandTest {
    private final GameHandler receiver;

    public ConfirmBetCommandTest() {
        receiver = MockGameModel.build();
    }

    @Test
    void execute() {
        assertNotNull(receiver);
    }
}