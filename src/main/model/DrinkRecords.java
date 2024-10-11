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

    // EFFECTS: returns the total amount of water in the list
    public int getWaterDrinkAmount() {
        int waterAmount = 0;
        for (DrinkRecord drink : drinkRecords) {
            if (drink.getType().equalsIgnoreCase("water")) {
                waterAmount += drink.getAmount();
            }
        }
        return waterAmount;
    }

    // EFFECTS: returns the total amount of other drinks (non-water) in the list
    public int getOtherDrinkAmount() {
        int otherDrinksAmount = 0;
        for (DrinkRecord drink : drinkRecords) {
            if (!drink.getType().equalsIgnoreCase("water")) {
                otherDrinksAmount += drink.getAmount();
            }
        }
        return otherDrinksAmount;
    }

     // EFFECTS: return true if there is water record, false otherwise
    public boolean isWater() {
        return true;
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

    // EFFECTS: returns true if there are no drink records, false otherwise
    public boolean contains(DrinkRecord drinkRecord) {
        return this.drinkRecords.contains(drinkRecord);
    }

    // EFFECTS: returns true if there are no drink records, false otherwise
    public boolean remove(DrinkRecord drinkRecord) {
        return this.drinkRecords.remove(drinkRecord);
    }
}
