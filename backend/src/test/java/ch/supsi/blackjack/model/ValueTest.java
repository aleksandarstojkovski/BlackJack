package ch.supsi.blackjack.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValueTest {

    @Test
    void testConstructor(){
        Value  v = Value.TEN;
        assertNotNull(v);
    }

    @Test
    void testGetLabel(){
        Value  v = Value.TEN;
        assertEquals(v.getLabel(),"10");
    }

    @Test
    void testGetDefaultValue(){
        Value  v = Value.TEN;
        assertEquals(v.getDefaultValue(),10);
    }

    @Test
    void testValuesLength(){
        Value  v = Value.TEN;
        assertEquals(Value.values().length, 13);
    }

}
