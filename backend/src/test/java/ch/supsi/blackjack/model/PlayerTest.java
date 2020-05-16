package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.exception.InsufficientCoinsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player testPlayer;

    @Test
    void addCard() {
        testPlayer = new Player("TestPlayer");
        var testCard = new Card.Builder(Value.ACE).build();
        testPlayer.addCard(testCard);
        assertEquals(1, testPlayer.hand.size());
    }

    @Test
    void getCoins() {
        testPlayer = new Player("TestPlayer");
        int startingCoin = testPlayer.getCoins();
        assertEquals(startingCoin,testPlayer.getCoins());
    }

    @Test
    void giveCoins() {
        testPlayer = new Player("TestPlayer");
        int startingCoin = testPlayer.getCoins();
        testPlayer.giveCoins(100);
        assertEquals(startingCoin+100,testPlayer.getCoins());

    }

    @Test
    void bet() throws InsufficientCoinsException {
        testPlayer = new Player("TestPlayer");
        int startingCoin = testPlayer.getCoins();
        testPlayer.bet(50);
        assertEquals(startingCoin-50,testPlayer.getCoins());
    }

    @Test
    void getHandValue() {
        testPlayer = new Player("TestPlayer");
        var testCard = new Card.Builder(Value.TEN).build();
        testPlayer.hand.addCard(testCard);
        assertEquals(10,testPlayer.getHandValue());

    }

}