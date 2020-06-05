package ch.supsi.blackjack.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VirtualPlayerTest {

    @Test
    void testConstructor(){
        VirtualPlayer vp = new VirtualPlayer("player", 100);
        assertNotNull(vp);
    }

}
