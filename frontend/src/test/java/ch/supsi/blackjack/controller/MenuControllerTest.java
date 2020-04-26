package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.event.GameStartedEvent;
import ch.supsi.blackjack.model.AbstractModel;
import ch.supsi.blackjack.model.Model;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.beans.PropertyChangeSupport;

class MenuControllerTest {

    @BeforeAll
    public static void beforeClass() {
        // WORKAROUND !!!
        // initialize javafx toolkit
        JFXPanel fxPanel = new JFXPanel();
    }

    @Test
    void initialState() {
        Model mockModel = MockModel.build();
        MenuController controller = new MenuController(mockModel);
        mockModel.addPropertyChangeListener(controller);

        assertFalse(controller.getDisableNewGame());
        assertTrue(controller.getDisableBet());
        assertTrue(controller.getDisableHitAndStand());
        assertTrue(controller.getDisableExitGame());
    }

    @Test
    void startGame() {
        // Mock Model
        Model mockModel = MockModel.build();
        // Real Controller
        MenuController controller = new MenuController(mockModel);
        mockModel.addPropertyChangeListener(controller);
        controller.newGameAction(null);

        assertTrue(controller.getDisableNewGame());
        assertTrue(controller.getDisableBet());
        assertTrue(controller.getDisableHitAndStand());
        assertFalse(controller.getDisableExitGame());
    }
}