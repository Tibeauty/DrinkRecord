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
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            DrinkRecords drinkRecords = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyDrinkRecords() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDrinkRecords.json");
        try {
            DrinkRecords drinkRecords = reader.read();
            List<DrinkRecord> testRecords = new ArrayList<>();
       //     assertEquals(testRecords, drinkRecords);
       // TODO: same problem
            assertEquals(0, drinkRecords.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDrinkRecords.json");
        try {
            DrinkRecords drinkRecords = reader.read();
            assertEquals(200, drinkRecords.getRecordAtIndex(1).getAmount());
            assertEquals("cola", drinkRecords.getRecordAtIndex(1).getType());
            assertEquals(100, drinkRecords.getRecordAtIndex(0).getAmount());
            assertEquals("water", drinkRecords.getRecordAtIndex(0).getType());
            assertEquals(3, drinkRecords.size());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
