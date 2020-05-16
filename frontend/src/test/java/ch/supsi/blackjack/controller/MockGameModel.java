package ch.supsi.blackjack.controller;

import ch.supsi.blackjack.event.*;
import ch.supsi.blackjack.model.GameModel;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class MockGameModel {
    public static GameModel build() {
        GameModel mockGameModel = Mockito.mock(GameModel.class);
        PropertyChangeSupport pcs = new PropertyChangeSupport(mockGameModel);

        // Property Change Listener
        Mockito.doAnswer((Answer<Void>) invocation -> {
            AbstractController c = invocation.getArgumentAt(0, AbstractController.class);
//            pcs.addPropertyChangeListener(c);
            return null;
        }).when(mockGameModel).addPropertyChangeListener(Mockito.any());

        // newGame
        Mockito.doAnswer((Answer<Void>) invocation -> {
            pcs.firePropertyChange(new GameStartedEvent(mockGameModel, null));
            return null;
        }).when(mockGameModel).newGame(Mockito.anyString());

        // exitGame
        Mockito.doAnswer((Answer<Void>) invocation -> {
            pcs.firePropertyChange(new GameFinishedEvent(mockGameModel));
            return null;
        }).when(mockGameModel).exitRound();

        // hit
        Mockito.doAnswer((Answer<Void>) invocation -> {
            pcs.firePropertyChange(new NewBetEvent(mockGameModel, 10 ));
            return null;
        }).when(mockGameModel).bet(Mockito.anyInt());

        // confirmBet
        Mockito.doAnswer((Answer<Void>) invocation -> {
            pcs.firePropertyChange(new BetConfirmedEvent(mockGameModel));
            return null;
        }).when(mockGameModel).confirmBet();

        // nextRound
        Mockito.doAnswer((Answer<Void>) invocation -> {
            pcs.firePropertyChange(new NewRoundEvent(mockGameModel, new ArrayList<>()));
            return null;
        }).when(mockGameModel).nextRound();

        // hit
        Mockito.doAnswer((Answer<Void>) invocation -> {
            pcs.firePropertyChange(new StandEvent(mockGameModel, null));
            return null;
        }).when(mockGameModel).stand();

        return mockGameModel;
    }
}
