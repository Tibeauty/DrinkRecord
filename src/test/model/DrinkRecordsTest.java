package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DrinkRecordsTest {
    private DrinkRecord d1;
    private DrinkRecord d2;

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
        d1.addAmount(150);
        assertEquals(250, d1.getAmount());
        d1.addAmount(200);
        assertEquals(450, d1.getAmount());
        d2.addAmount(10);
        assertEquals(310, d2.getAmount());
    }

    @Test
    void testSetters() {
        d1.setAmount(400);
        assertEquals(400, d1.getAmount());
        d2.setType("soda");
        assertEquals("soda", d2.getType());
    }

    @Test
    void testGetters() {
        assertEquals("cola", d2.getType());
        assertEquals(100, d1.getAmount());
    }
}
