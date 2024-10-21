package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

// Represents a reader that reads DrinkRecords from JSON data and stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads DrinkRecords from file and returns it;
    // throws IOException if an error occurs reading data from file
    public DrinkRecords read() throws IOException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        DrinkRecords drinkRecords = new DrinkRecords();
        return addDrinkRecords(drinkRecords, jsonArray);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // MODIFIES: drinkRecords
    // EFFECTS: parses drink records from JSON object and adds them to the list
    private DrinkRecords addDrinkRecords(DrinkRecords drinkRecords, JSONArray jsonArray) {
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addDrinkRecord(drinkRecords, nextThingy);
        }
        return drinkRecords;
    }

     // MODIFIES: drinkRecords
    // EFFECTS: parses a single drink record from JSON object and adds it to the list
    private void addDrinkRecord(DrinkRecords drinkRecords, JSONObject jsonObject) {
        String type = jsonObject.getString("type");
        int amount = jsonObject.getInt("amount");
        DrinkRecord drinkRecord = new DrinkRecord(type, amount);
        drinkRecords.addDrinkRecord(drinkRecord);
    }
}
