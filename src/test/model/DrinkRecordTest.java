package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DrinkRecordTest {
    private DrinkRecords drinkRecords;
    private DrinkRecord water;
    private DrinkRecord juice;


    @BeforeEach
    void runBefore() {
        drinkRecords = new DrinkRecords();
        water = new DrinkRecord("water", 500);
        juice = new DrinkRecord("juice", 250);
    }

    @Test
    void testConstructor() {
        assertTrue(drinkRecords.isEmpty());
        assertEquals(0, drinkRecords.size());
    }


    @Test
    void testSetters() {

    }

    @Test
    void testGetters() {

    }
}
