package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
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
    void testGetDrinkRecords() {
        drinkRecords.addDrinkRecord(water);
        drinkRecords.addDrinkRecord(juice);
        List<DrinkRecord> testRecords = new ArrayList<>();
        testRecords.add(water);
        testRecords.add(juice);
        assertEquals(drinkRecords.getDrinkRecords(), testRecords);
        assertEquals(drinkRecords.getDrinkRecords(), testRecords);
    }


    @Test
    void testDrinkRecordsToJson() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(water.toJson());
        jsonArray.put(juice.toJson());

        drinkRecords.addDrinkRecord(water);
        drinkRecords.addDrinkRecord(juice);
        assertEquals(drinkRecords.drinkRecordsToJson().toString(), jsonArray.toString());
    }

    @Test
    void testGetFeedbackWhenEmpty() {
        assertFalse(drinkRecords.getFeedback());
    }

    @Test
    void testGetFeedbackWithMoreWater() {
        drinkRecords.addDrinkRecord(water); 
        drinkRecords.addDrinkRecord(juice); 

        assertTrue(drinkRecords.getFeedback());
    }

    @Test
    void testGetFeedbackWithMoreOtherDrinks() {
        DrinkRecord soda = new DrinkRecord("soda", 400); 
        drinkRecords.addDrinkRecord(juice); 
        drinkRecords.addDrinkRecord(soda); 


        drinkRecords.addDrinkRecord(water); // 500 ml water
        assertFalse(drinkRecords.getFeedback());
    }

    @Test
    void testGetFeedbackWithEqualAmounts() {
        drinkRecords.addDrinkRecord(new DrinkRecord("water", 300));
        drinkRecords.addDrinkRecord(new DrinkRecord("juice", 300));

        assertTrue(drinkRecords.getFeedback());
    }
}
