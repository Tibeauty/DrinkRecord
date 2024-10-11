package model;

import java.util.ArrayList;
import java.util.List;

public class DrinkRecords {
    private List<DrinkRecord> drinkRecords;

    // MODIFIES: this
    // EFFECTS: initalize
    public DrinkRecords() { 
        this.drinkRecords = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a drink record to the list
    public void addDrinkRecord(DrinkRecord drinkRecord) {
        this.drinkRecords.add(drinkRecord);
    }

    // EFFECTS: returns true if there are no drink records, false otherwise
    public boolean isEmpty() {
        return this.drinkRecords.isEmpty();
    }

    // EFFECTS: returns the size of the drink records list
    public int size() {
        return this.drinkRecords.size();
    }

    // EFFECTS: returns the drink record at a specified index
    public DrinkRecord getRecordAtIndex(int index) {
        return this.drinkRecords.get(index);
    }
}
