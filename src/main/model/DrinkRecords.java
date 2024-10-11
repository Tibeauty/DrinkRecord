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
}
