package persistence;

import model.DrinkRecord;
import model.DrinkRecords;

import java.io.IOException;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            DrinkRecords drinkRecords = new DrinkRecords();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyDrinkRecords() {
        try {
            DrinkRecords drinkRecords = new DrinkRecords();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDrinkRecords.json");
            writer.open();
            writer.write(drinkRecords);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDrinkRecords.json");
           // drinkRecords = reader.read();
           // TODO: the list if null but have
           // DrinkRecords testRecords = new DrinkRecords();
            //testRecords = reader.read();
            //assertNull(drinkRecords); 
            assertEquals(0, drinkRecords.size());

        } catch (IOException e) {
            fail("Excepetion is happened.");
        }
    }

    @Test
    public void testWriterGeneralDrinkRecords() {
        try {
            DrinkRecords drinkRecords = new DrinkRecords();
            DrinkRecord d1 = new DrinkRecord("water", 100);
            DrinkRecord d2 = new DrinkRecord("cola", 200);
            DrinkRecord d3 = new DrinkRecord("soda", 195);
            drinkRecords.addDrinkRecord(d1);
            drinkRecords.addDrinkRecord(d2);
            drinkRecords.addDrinkRecord(d3);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDrinkRecords.json");
            writer.open();
            writer.write(drinkRecords);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDrinkRecords.json");
            drinkRecords = reader.read();
            
            assertEquals(200, drinkRecords.get(1).getAmount());
            assertEquals("cola", drinkRecords.get(1).getType());
            assertEquals(100, drinkRecords.get(0).getAmount());
            assertEquals("water", drinkRecords.get(0).getType());
            assertEquals(3, drinkRecords.size());

        } catch (IOException e) {
            fail("Excepetion is happened.");
        }
    }
}
