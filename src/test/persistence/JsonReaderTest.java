package persistence;

import model.DrinkRecord;
import model.DrinkRecords;

import java.io.IOException;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            DrinkRecords drinkRecords = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyDrinkRecords() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDrinkRecords.json");
        try {
            DrinkRecords drinkRecords = reader.read();
            List<DrinkRecord> testRecords = new ArrayList<>();
            assertEquals(testRecords, drinkRecords);
            assertEquals(0, drinkRecords.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDrinkRecords.json");
        try {
            DrinkRecords drinkRecords = reader.read();
            assertEquals(new DrinkRecord("cola", 200), drinkRecords.get(1));
            assertEquals(new DrinkRecord("water", 100), drinkRecords.get(0));
            assertEquals(3, drinkRecords.size());
            
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
