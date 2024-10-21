package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.json.JSONObject;

// Represents a reader that reads DrinkRecords from JSON data and stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {

    }

    // EFFECTS: reads DrinkRecords from file and returns it;
    // throws IOException if an error occurs reading data from file
    public DrinkRecords read() throws IOException {

    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {

    }

    // MODIFIES: drinkRecords
    // EFFECTS: parses drink records from JSON object and adds them to the list
    private void addDrinkRecords(DrinkRecords drinkRecords, JSONObject jsonObject) {

    }

     // MODIFIES: drinkRecords
    // EFFECTS: parses a single drink record from JSON object and adds it to the list
    private void addDrinkRecord(DrinkRecords drinkRecords, JSONObject jsonObject) {

    }
}
