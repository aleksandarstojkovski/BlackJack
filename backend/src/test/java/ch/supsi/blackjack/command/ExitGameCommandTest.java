package ch.supsi.blackjack.command;

import ch.supsi.blackjack.model.GameHandler;
import ch.supsi.blackjack.model.MockGameModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ExitGameCommandTest {
    private final GameHandler receiver;
    public ExitGameCommandTest() {
        receiver = MockGameModel.build();
    }

    @Test
    void constructor() {
        ExitGameCommand command = new ExitGameCommand(receiver);
        assertNotNull(command);
    }


    @Test
    void execute() {
        ExitGameCommand command = new ExitGameCommand(receiver);
        command.execute();
        Mockito.verify(receiver, Mockito.times(1)).exitRound();
    }
}