package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.event.*;
import ch.supsi.blackjack.model.Card;
import ch.supsi.blackjack.model.Model;
import ch.supsi.blackjack.model.Player;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

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

        // newGame
        Mockito.doAnswer((Answer<Void>) invocation -> {
            pcs.firePropertyChange(new GameStartedEvent(mockModel, null));
            return null;
        }).when(mockModel).newGame(Mockito.anyString());

        // exitGame
        Mockito.doAnswer((Answer<Void>) invocation -> {
            pcs.firePropertyChange(new GameFinishedEvent( mockModel));
            return null;
        }).when(mockModel).exitGame();

        // hit
        Mockito.doAnswer((Answer<Void>) invocation -> {
            pcs.firePropertyChange(new NewBetEvent(mockModel, 10 ));
            return null;
        }).when(mockModel).bet(Mockito.anyInt());

        // confirmBet
        Mockito.doAnswer((Answer<Void>) invocation -> {
            pcs.firePropertyChange(new BetConfirmedEvent( mockModel));
            return null;
        }).when(mockModel).confirmBet();

        // nextRound
        Mockito.doAnswer((Answer<Void>) invocation -> {
            pcs.firePropertyChange(new NewRoundEvent(mockModel, new ArrayList<>()));
            return null;
        }).when(mockModel).nextRound();

        // hit
        Mockito.doAnswer((Answer<Void>) invocation -> {
            pcs.firePropertyChange(new StandEvent(mockModel, null));
            return null;
        }).when(mockModel).stand();

        return mockModel;
    }
}
