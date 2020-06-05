package ch.supsi.blackjack;

import ch.supsi.blackjack.command.*;
import ch.supsi.blackjack.model.GameModel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class CommandCatalogTest {
    private final GameModel receiver;
    CommandCatalogTest(){
        receiver = Mockito.mock(GameModel.class);
    }
    @Test
    void execute0() {
        CommandCatalog commandCatalog = new CommandCatalog(receiver);
        commandCatalog.execute("hitAction");
        Mockito.verify(receiver, Mockito.times(1)).hit();
    }
    @Test
    void execute1() {
        CommandCatalog commandCatalog = new CommandCatalog(receiver);
        commandCatalog.execute("standAction");
        Mockito.verify(receiver, Mockito.times(1)).stand();
    }
    @Test
    void execute2() {
        CommandCatalog commandCatalog = new CommandCatalog(receiver);
        commandCatalog.execute("betAction");
        Mockito.verify(receiver, Mockito.times(1)).confirmBet();
    }
    @Test
    void execute3() {
        CommandCatalog commandCatalog = new CommandCatalog(receiver);
        commandCatalog.execute("nextRoundAction");
        Mockito.verify(receiver, Mockito.times(1)).nextRound();

    }
    @Test
    void execute4() {
        CommandCatalog commandCatalog = new CommandCatalog(receiver);
        commandCatalog.execute("newGameAction");
        Mockito.verify(receiver, Mockito.times(1)).newGame();
    }
    @Test
    void execute5() {
        CommandCatalog commandCatalog = new CommandCatalog(receiver);
        commandCatalog.execute("exitGameAction");
        Mockito.verify(receiver, Mockito.times(1)).exitRound();
    }
    @Test
    void execute6() {
        CommandCatalog commandCatalog = new CommandCatalog(receiver);
        commandCatalog.execute("bet100Action");
        commandCatalog.execute("bet200Action");
        commandCatalog.execute("bet300Action");
        commandCatalog.execute("bet400Action");
        commandCatalog.execute("bet500Action");
        Mockito.verify(receiver, Mockito.times(5)).bet(Mockito.anyInt());
    }
}