package ch.supsi.blackjack.command;

import ch.supsi.blackjack.model.GameHandler;
import ch.supsi.blackjack.model.MockGameModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class StandCommandTest {
    private final GameHandler receiver;

    public StandCommandTest() {
        receiver = MockGameModel.build();
    }

    @Test
    void constructor() {
        StandCommand command = new StandCommand(receiver);
        assertNotNull(command);
    }


    @Test
    void execute() {
        StandCommand command = new StandCommand(receiver);
        command.execute();
        Mockito.verify(receiver, Mockito.times(1)).stand();
    }
}