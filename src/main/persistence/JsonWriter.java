package persistence;

import model.DrinkRecords;
import org.json.JSONObject;


import java.io.*;

// Represents a writer that writes Json representation of DrinkRecords to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {

    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {

    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of DrinkRecords to file
    public void write(DrinkRecords drinkRecords) {

    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
    }
}
