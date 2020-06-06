package ch.supsi.blackjack;

import ch.supsi.blackjack.model.GameModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandCatalogTest {
    private GameModel receiver;
    private CommandCatalog commandCatalog;

    @BeforeEach
    void setup() {
        receiver = Mockito.mock(GameModel.class);
        commandCatalog = new CommandCatalog(receiver);
    }

    @Test
    void execute0() {
        commandCatalog.execute("hitAction");
        Mockito.verify(receiver, Mockito.times(1)).hit();
    }
    @Test
    void execute1() {
        commandCatalog.execute("standAction");
        Mockito.verify(receiver, Mockito.times(1)).stand();
    }
    @Test
    void execute2() {
        commandCatalog.execute("betAction");
        Mockito.verify(receiver, Mockito.times(1)).confirmBet();
    }
    @Test
    void execute3() {
        commandCatalog.execute("nextRoundAction");
        Mockito.verify(receiver, Mockito.times(1)).nextRound();

    }
    @Test
    void execute4() {
        commandCatalog.execute("newGameAction");
        Mockito.verify(receiver, Mockito.times(1)).newGame();
    }
    @Test
    void execute5() {
        commandCatalog.execute("exitGameAction");
        Mockito.verify(receiver, Mockito.times(1)).exitRound();
    }
    @Test
    void execute6() {
        commandCatalog.execute("bet100Action");
        commandCatalog.execute("bet200Action");
        commandCatalog.execute("bet300Action");
        commandCatalog.execute("bet400Action");
        commandCatalog.execute("bet500Action");
        Mockito.verify(receiver, Mockito.times(5)).bet(Mockito.anyInt());
    }

    @Test
    void executeInvalid() {
        assertThrows(IllegalStateException.class, () -> commandCatalog.execute("invalid_command") );
    }
}