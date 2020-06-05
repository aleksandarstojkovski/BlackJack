package ch.supsi.blackjack.command;

import ch.supsi.blackjack.model.GameHandler;
import ch.supsi.blackjack.model.MockGameModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HitCommandTest {
    private final GameHandler receiver;

    public HitCommandTest() {
        receiver = MockGameModel.build();
    }

    @Test
    void constructor() {
        HitCommand command = new HitCommand(receiver);
        assertNotNull(command);
    }


    @Test
    void execute() {
        HitCommand command = new HitCommand(receiver);
        command.execute();
        Mockito.verify(receiver, Mockito.times(1)).hit();
    }
}