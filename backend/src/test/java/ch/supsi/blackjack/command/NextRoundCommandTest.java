package ch.supsi.blackjack.command;

import ch.supsi.blackjack.model.GameHandler;
import ch.supsi.blackjack.model.MockGameModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class NextRoundCommandTest {
    private final GameHandler receiver;

    public NextRoundCommandTest() {
        receiver = MockGameModel.build();
    }

    @Test
    void constructor() {
        NextRoundCommand command = new NextRoundCommand(receiver);
        assertNotNull(command);
    }


    @Test
    void execute() {
        NextRoundCommand command = new NextRoundCommand(receiver);
        command.execute();
        Mockito.verify(receiver, Mockito.times(1)).nextRound();
    }
}