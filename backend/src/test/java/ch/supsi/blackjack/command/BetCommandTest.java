package ch.supsi.blackjack.command;

import ch.supsi.blackjack.model.GameHandler;
import ch.supsi.blackjack.model.GameModel;
import ch.supsi.blackjack.model.MockGameModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class BetCommandTest {

    private final int bet;
    private final GameHandler receiver;

    BetCommandTest() {
        this.bet = 100;
        this.receiver = Mockito.mock(GameModel.class);
    }

    @Test
    void constructor() {
        BetCommand command = new BetCommand(receiver, bet);
        assertNotNull(command);
    }

    @Test
    void execute0() {
        BetCommand command = new BetCommand(null, bet);
        command.execute();
        Mockito.verify(receiver, Mockito.times(0)).bet(Mockito.anyInt());
    }
    @Test
    void execute1() {
        BetCommand command = new BetCommand(receiver, bet);
        command.execute();
        Mockito.verify(receiver, Mockito.times(1)).bet(bet);
    }
}