package ch.supsi.blackjack.model;

import ch.supsi.blackjack.event.*;
import ch.supsi.blackjack.model.state.round.BetState;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    void testAbstractEvent(){
        // test first constructir
        AbstractEvent o1 = new AbstractEvent(new Object());
        // test second constructor
        AbstractEvent o2 = new AbstractEvent(new Object(),"",new Object(), "");
        assertNotNull(o1);
        assertNotNull(o2);
    }

    @Test
    void testBetConfirmedEvent(){
        // test first constructir
        BetConfirmedEvent o1 = new BetConfirmedEvent(new Object());
        assertNotNull(o1);
    }

    @Test
    void testDealerBustedEvent(){
        // test first constructir
        DealerBustedEvent o1 = new DealerBustedEvent(new Object());
        assertNotNull(o1);
    }

    @Test
    void testDealerCompletedEvent(){
        // test first constructir
        DealerCompletedEvent o1 = new DealerCompletedEvent(new Object());
        assertNotNull(o1);
    }

    @Test
    void testDealerHandUpdateEvent(){
        Card.Builder cardBuilder = new Card.Builder();
        Value value = Value.TEN;
        cardBuilder.setValue(value);
        Card card = cardBuilder.build();
        Hand hand = new Hand();
        hand.addCard(card);
        BetState betState = new BetState(new RoundMediator(GameModel.instance(), "player","dealer"));
        // test first constructir
        DealerHandUpdateEvent o1 = new DealerHandUpdateEvent(new Object(),hand,betState);
        assertNotNull(o1);
        // test getLastCard
        assertEquals(o1.getLastCard(),card);
        // test getValue
        assertEquals(o1.getValue(),value.getDefaultValue());
        // test getState
        assertEquals(betState, o1.getState());
        // test getHandSize
        assertEquals(o1.getHandSize(), hand.size());
    }

    @Test
    void testGameFinishedEvent(){
        // test first constructir
        GameFinishedEvent o1 = new GameFinishedEvent(new Object());
        assertNotNull(o1);
    }

    @Test
    void testGameOverEvent(){
        // test first constructir
        GameOverEvent o1 = new GameOverEvent(new Object());
        assertNotNull(o1);
    }

    @Test
    void testGameStandEvent(){
        // test first constructir
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player("",100));
        GameStartedEvent o1 = new GameStartedEvent(new Object(),playerList);
        assertNotNull(o1);

        // test getPlayerList
        assertEquals(o1.getPlayerList(), playerList);
    }


    @Test
    void testNewBetEvent(){
        // test first constructir
        int betValue = 100;
        NewBetEvent o1 = new NewBetEvent(new Object(),betValue);
        assertNotNull(o1);
        // getGetValue
        assertEquals(o1.getBetValue(),betValue);
    }

    @Test
    void testNewRoundEvent(){
        // test first constructir
        List<Player> playerList = new ArrayList<>();
        NewRoundEvent o1 = new NewRoundEvent(new Object(),playerList);
        assertNotNull(o1);
        assertEquals(playerList,o1.getPlayerList());
    }

    @Test
    void testPlayerBlackJackEvent(){
        // test first constructir
        PlayerBlackjackEvent o1 = new PlayerBlackjackEvent(new Object());
        assertNotNull(o1);
    }

    @Test
    void testPlayerBustedEvent(){
        // test first constructir
        PlayerBustedEvent o1 = new PlayerBustedEvent(new Object());
        assertNotNull(o1);
    }

    @Test
    void testPlayerHandUpdateEvent(){
        Card.Builder cardBuilder = new Card.Builder();
        Value value = Value.TEN;
        cardBuilder.setValue(value);
        Card card = cardBuilder.build();
        Hand hand = new Hand();
        hand.addCard(card);
        // test first constructir
        PlayerHandUpdateEvent o1 = new PlayerHandUpdateEvent(new Object(),hand);
        assertNotNull(o1);
        assertEquals(o1.getLastCard(), card);
        assertEquals(o1.getValue(), value.getDefaultValue());
    }

    @Test
    void testPlayerTwentyOneEvent(){
        // test first constructir
        PlayerTwentyOneEvent o1 = new PlayerTwentyOneEvent(new Object());
        assertNotNull(o1);
    }

    @Test
    void testRoundCompletedEvent(){
        // test first constructir
        RoundResult result = RoundResult.LOOSE;
        RoundCompletedEvent o1 = new RoundCompletedEvent(new Object(),RoundResult.LOOSE);
        assertNotNull(o1);
        assertEquals(o1.getRoundResult(),result);
    }

    @Test
    void testStandEvent(){
        // test first constructir
        Player p1 = new Player("",100);
        StandEvent o1 = new StandEvent(new Object(),p1);
        assertNotNull(o1);
        assertEquals(o1.getPlayer(),p1);
    }

}
