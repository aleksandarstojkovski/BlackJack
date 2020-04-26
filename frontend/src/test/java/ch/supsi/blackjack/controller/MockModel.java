package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.event.GameStartedEvent;
import ch.supsi.blackjack.model.Model;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.beans.PropertyChangeSupport;

public class MockModel {
    public static Model build() {
        Model mockModel = Mockito.mock(Model.class);
        PropertyChangeSupport pcs = new PropertyChangeSupport(mockModel);

        // Property Change Listener
        Mockito.doAnswer((Answer<Void>) invocation -> {
            AbstractController c = invocation.getArgumentAt(0, AbstractController.class);
            pcs.addPropertyChangeListener(c);
            return null;
        }).when(mockModel).addPropertyChangeListener(Mockito.any());

        // new Game
        Mockito.doAnswer((Answer<Void>) invocation -> {
            pcs.firePropertyChange(new GameStartedEvent(mockModel, null));
            return null;
        }).when(mockModel).newGame(Mockito.anyString());

        return mockModel;
    }
}
