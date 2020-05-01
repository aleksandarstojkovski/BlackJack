package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.exception.InsufficientCoinsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player testPlayer;

    @Test
    void addCard() {
        testPlayer = new Player("TestPlayer");
        Card testCard = new Card(Seed.S,Value.ACE,"blu");
        testPlayer.addCard(testCard);
        assertEquals(1,testPlayer.hand.size());
    }

    @Test
    void getCoins() {
        testPlayer = new Player("TestPlayer");
        testPlayer.giveCoins(100);
        assertEquals(1100,testPlayer.getCoins());
    }

    @Test
    void giveCoins() {
        testPlayer = new Player("TestPlayer");
        assertEquals(1000,testPlayer.getCoins());
        testPlayer.giveCoins(100);
        assertEquals(1100,testPlayer.getCoins());

    }

    @Test
    void bet() throws InsufficientCoinsException {
        testPlayer = new Player("TestPlayer");
        testPlayer.giveCoins(100);
        testPlayer.bet(50);
        assertEquals(1050,testPlayer.getCoins());
    }

    @Test
    void getHandValue() {
        testPlayer = new Player("TestPlayer");
        Card testCard = new Card(Seed.S,Value.TEN,"blu");
        testPlayer.hand.addCard(testCard);
        assertEquals(10,testPlayer.getHandValue());

    }
}