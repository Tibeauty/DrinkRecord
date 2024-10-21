package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DrinkRecordsTest {
    private DrinkRecords drinkRecords;
    private DrinkRecords drinkRecords2;
    private DrinkRecord water;
    private DrinkRecord juice;


    @BeforeEach
    void runBefore() {
        drinkRecords = new DrinkRecords();
        drinkRecords2 = new DrinkRecords();
        water = new DrinkRecord("water", 500);
        juice = new DrinkRecord("juice", 250);
    }

    @Test
    void testConstructor() {
        assertTrue(drinkRecords.isEmpty());
        assertEquals(0, drinkRecords.size());
    }

    @Test
    void addDrinkRecord() {
        drinkRecords.addDrinkRecord(juice);
        drinkRecords.addDrinkRecord(water);
        assertEquals(2, drinkRecords.size());
        assertEquals(juice, drinkRecords.getRecordAtIndex(0));
        assertEquals(water, drinkRecords.getRecordAtIndex(1));      
    }

    @Test
    void testGetWaterDrinkAmount() {
        drinkRecords.addDrinkRecord(water);
        drinkRecords.addDrinkRecord(juice);
        drinkRecords2.addDrinkRecord(juice);
        assertEquals(500, drinkRecords.getWaterDrinkAmount());
        assertEquals(0, drinkRecords2.getWaterDrinkAmount());
    }

    @Test
    void testGetOtherDrinkAmount() {
        drinkRecords.addDrinkRecord(water);
        drinkRecords.addDrinkRecord(juice);
        drinkRecords2.addDrinkRecord(water);
        assertEquals(250, drinkRecords.getOtherDrinkAmount());
        assertEquals(0, drinkRecords2.getOtherDrinkAmount());
    }

    @Test
    void testIsEmpty() {
        assertTrue(drinkRecords.isEmpty());
        drinkRecords.addDrinkRecord(water);
        assertFalse(drinkRecords.isEmpty());
    }

    @Test
    void testSize() {
        assertEquals(0, drinkRecords.size());
        drinkRecords.addDrinkRecord(water);
        drinkRecords.addDrinkRecord(juice);
        assertEquals(2, drinkRecords.size());
    }

    @Test
    void testGetRecordAtIndex() {
        drinkRecords.addDrinkRecord(water);
        drinkRecords.addDrinkRecord(juice);
        assertEquals(water, drinkRecords.getRecordAtIndex(0));
        assertEquals(juice, drinkRecords.getRecordAtIndex(1));
    }

    @Test
    void testContains() {
        drinkRecords.addDrinkRecord(water);
        assertTrue(drinkRecords.contains(water));
        assertFalse(drinkRecords.contains(juice));
    }

    @Test
    void testRemove() {
        drinkRecords.addDrinkRecord(water);
        drinkRecords.addDrinkRecord(juice);
        
        // Ensure the list contains the records before removal
        assertTrue(drinkRecords.contains(water));
        assertTrue(drinkRecords.contains(juice));
        assertEquals(2, drinkRecords.size());
        
        // Remove a record and check if it was removed
        drinkRecords.remove(water);
        assertFalse(drinkRecords.contains(water));
        assertEquals(1, drinkRecords.size());
    }

    @Test
    void testGet() {
        drinkRecords.addDrinkRecord(water);
        drinkRecords.addDrinkRecord(juice);
        assertEquals(drinkRecords.get(0), water);
        assertEquals(drinkRecords.get(1), juice);
    }
}
