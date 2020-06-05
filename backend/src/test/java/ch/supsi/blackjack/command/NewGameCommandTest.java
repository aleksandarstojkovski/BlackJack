package ch.supsi.blackjack.command;

import ch.supsi.blackjack.model.GameHandler;
import ch.supsi.blackjack.model.MockGameModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class NewGameCommandTest {
    private final GameHandler receiver;

    public NewGameCommandTest() {
        receiver = MockGameModel.build();
    }

    @Test
    void constructor() {
        NewGameCommand command = new NewGameCommand(receiver);
        assertNotNull(command);
    }


    @Test
    void execute() {
        NewGameCommand command = new NewGameCommand(receiver);
        command.execute();
        Mockito.verify(receiver, Mockito.times(1)).newGame();
    }
}