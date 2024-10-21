package model;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DrinkRecordTest {
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

    @Test
    void testToJson() {
        JSONObject testJson = new JSONObject();
        testJson.put("type", "water");
        testJson.put("amount", 100);
        assertEquals(testJson, d1.toJson());
    }
}