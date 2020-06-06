package ch.supsi.blackjack.model;

import ch.supsi.blackjack.model.exception.InsufficientCoinsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private final int initialCoins = 1000;
    private String nickName = "TestPlayer";

    @Test
    void testFirstConstructor() {
        Player testPlayer = new Player(nickName, initialCoins);
        assertNotNull(testPlayer);
    }

    @Test
    void testSecondConstructor() {
        Player testPlayer = new Player(nickName);
        assertNotNull(testPlayer);
    }

    @Test
    void getNickname() {
        Player testPlayer = new Player(nickName, initialCoins);
        assertEquals(nickName, testPlayer.getNickname());
    }

    @Test
    void hasMoney() {
        Player testPlayer = new Player(nickName,initialCoins);
        assertTrue(testPlayer.hasMoney());
    }

    @Test
    void giveCoins() {
        Player testPlayer = new Player(nickName,initialCoins);
        testPlayer.giveCoins(initialCoins);
        assertEquals(initialCoins*2, testPlayer.getCoinsAmount());
    }

    @Test
    void takeCoins() {
        Player testPlayer = new Player(nickName,initialCoins);
        try {
            testPlayer.takeCoins(initialCoins);
        } catch (InsufficientCoinsException e) {
            fail();
        }
        assertFalse(testPlayer.hasMoney());
    }

    @Test
    void coinsException() {
        Player testPlayer = new Player(nickName,initialCoins);
        assertThrows(InsufficientCoinsException .class, ()->testPlayer.takeCoins(initialCoins*2));
    }


}