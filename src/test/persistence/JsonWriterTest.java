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
    void testWriterInvalidFile() {
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
    void testWriterEmptyDrinkRecords() {
        try {
            DrinkRecords drinkRecords = new DrinkRecords();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDrinkRecords.json");
            writer.open();
            writer.write(drinkRecords);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDrinkRecords.json");
            drinkRecords = reader.read();
            List<DrinkRecord> testRecords = new ArrayList<>();
            assertEquals(testRecords, drinkRecords);
            assertEquals(0, drinkRecords.size());

        } catch (IOException e) {
            fail("Excepetion is happened.");
        }
    }

    @Test
    void testWriterGeneralDrinkRecords() {
        try {
            DrinkRecords drinkRecords = new DrinkRecords();
            drinkRecords.addDrinkRecord(new DrinkRecord("water", 100));
            drinkRecords.addDrinkRecord(new DrinkRecord("cola", 200));
            drinkRecords.addDrinkRecord(new DrinkRecord("soda", 195));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDrinkRecords.json");
            writer.open();
            writer.write(drinkRecords);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDrinkRecords.json");
            drinkRecords = reader.read();
            
            assertEquals(new DrinkRecord("cola", 200), drinkRecords.get(1));
            assertEquals(new DrinkRecord("water", 100), drinkRecords.get(0));
            assertEquals(3, drinkRecords.size());

        } catch (IOException e) {
            fail("Excepetion is happened.");
        }
    }
}
