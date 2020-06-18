package ch.supsi.blackjack.model;

import ch.supsi.blackjack.event.*;
import ch.supsi.blackjack.model.state.game.InitState;
import ch.supsi.blackjack.model.state.round.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoundMediatorTest {

    RoundMediator round;
    GameModel model;

    @BeforeEach
    void setup()  {
        model = Mockito.mock(GameModel.class);
    }

    @Test
    void getGameModel() {
        round = new RoundMediator(model,"Player1","Dealer1");
        assertEquals(model, round.getGameModel());
    }

    @Test
    void openRound() {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new BetState(round));
        round.playerHit();
        verify(model, times(1)).firePropertyChange(Matchers.isA(PlayerHandUpdateEvent.class));
    }

    @Test
    void playerHit()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new BetState(round));
        round.playerHit();
        verify(model, times(1)).firePropertyChange(Matchers.isA(PlayerHandUpdateEvent.class));
    }

    @Test
    void exitRound()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new BetState(round));
        round.exitRound();
        verify(model, times(1)).firePropertyChange(Matchers.isA(GameFinishedEvent.class));
    }

    @Test
    void nextRound()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new BetState(round));
        round.nextRound();
        verify(model, times(1)).firePropertyChange(Matchers.isA(NewRoundEvent.class));
    }

    @Test
    void startRound()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new BetState(round));
        round.startRound();
        verify(model, times(1)).firePropertyChange(Matchers.isA(GameStartedEvent.class));
    }

    @Test
    void setPlayerStand()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new BetState(round));
        round.setPlayerStand();
        verify(model, times(1)).firePropertyChange(Matchers.isA(StandEvent.class));
    }

    @Test
    void isBetConfirmed()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new BetState(round));
        assertNotNull(round.isBetConfirmed());
        assertFalse(round.isBetConfirmed());
    }

    @Test
    void isPlayerWithMoney()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new BetState(round));
        assertNotNull(round.isPlayerWithMoney());
        assertTrue(round.isPlayerWithMoney());
    }

    @Test
    void isPlayerHandBusted()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new BetState(round));
        assertNotNull(round.isPlayerHandBusted());
        assertFalse(round.isPlayerHandBusted());
    }

    @Test
    void isPlayerHandBlackjack()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new BetState(round));
        assertNotNull(round.isPlayerHandBlackjack());
        assertFalse(round.isPlayerHandBlackjack());
    }

    @Test
    void isPlayerStand()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new BetState(round));
        assertNotNull(round.isPlayerStand());
        assertFalse(round.isPlayerStand());
    }

    @Test
    void isDealerHandBusted(){
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new BetState(round));
        assertNotNull(round.isDealerHandBusted());
        assertFalse(round.isDealerHandBusted());
    }

    @Test
    void playerConfirmBet()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new BetState(round));
        round.playerConfirmBet();
        verify(model, times(1)).firePropertyChange(Matchers.isA(BetConfirmedEvent.class));
    }

    @Test
    void setPlayerBusted()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new BetState(round));
        round.setPlayerBusted();
        verify(model, times(1)).firePropertyChange(Matchers.isA(PlayerBustedEvent.class));
    }

    @Test
    void setPlayerBlackjack()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new BetState(round));
        round.setPlayerBlackjack();
        verify(model, times(1)).firePropertyChange(Matchers.isA(PlayerBlackjackEvent.class));
    }

    @Test
    void setPlayerTwentyOne()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new BetState(round));
        round.setPlayerTwentyOne();
        verify(model, times(1)).firePropertyChange(Matchers.isA(PlayerTwentyOneEvent.class));
    }

    @Test
    void setRoundCompleted()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new BetState(round));
        round.setRoundCompleted();
        verify(model, times(1)).firePropertyChange(Matchers.isA(RoundCompletedEvent.class));
    }

    @Test
    void setDealerBusted()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new BetState(round));
        round.setDealerBusted();
        verify(model, times(1)).firePropertyChange(Matchers.isA(DealerBustedEvent.class));
    }

    @Test
    void updateDealer()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new BetState(round));
        round.updateDealer();
        verify(model, times(1)).firePropertyChange(Matchers.isA(DealerCompletedEvent.class));
    }

    @Test
    void setGameOver()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new BetState(round));
        round.setGameOver();
        verify(model, times(1)).firePropertyChange(Matchers.isA(GameOverEvent.class));
    }

    @Test
    void setStateBet()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new BetState(round));
        assertNotEquals(new BetState(round), round.getState());
    }

    @Test
    void setStateBlackJack()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new BlackJackState(round));
        assertNotEquals(new BlackJackState(round), round.getState());
    }

    @Test
    void setStateDealerBust()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new DealerBustState(round));
        assertNotEquals(new DealerBustState(round), round.getState());
    }

    @Test
    void setStateDealerDeals()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new DealerDealsState(round));
        assertNotEquals(new DealerDealsState(round), round.getState());
    }

    @Test
    void setStateGameOver()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new GameOverState(round));
        assertNotEquals(new GameOverState(round), round.getState());
    }

    @Test
    void setStatePlayerBust()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new PlayerBustState(round));
        assertNotEquals(new PlayerBustState(round), round.getState());
    }

    @Test
    void setStatePlayerDeals()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new PlayerDealsState(round));
        assertNotEquals(new PlayerDealsState(round), round.getState());
    }

    @Test
    void setStateSetupTable()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new SetupTableState(round));
        assertNotEquals(new SetupTableState(round), round.getState());
    }

    @Test
    void setStateTwentyOne()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new TwentyOneState(round));
        assertNotEquals(new TwentyOneState(round), round.getState());
    }

    @Test
    void setStateUpdateTable()  {
        round = new RoundMediator(model,"Player1","Dealer1");
        round.setState(new UpdateTableState(round));
        assertNotEquals(new UpdateTableState(round), round.getState());
    }

}