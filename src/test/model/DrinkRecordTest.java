package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DrinkRecordTest {
    DrinkRecord d1;
    DrinkRecord d2;

    @BeforeEach
    void runBefore() {
        d1 = new DrinkRecord("water", 100);
        d2 = new DrinkRecord("cola", 300);
    }

    @Test
    void testConstructor() {
        assertEquals("water", d1.getType());
        assertEquals("cola", d2.getType());
        assertEquals(100, d1.getAmount());
    }

    @Test
    void testaddamount() {

    }

    @Test
    void testSetters() {

    }

    @Test
    void testGetters() {
        
    }
}
