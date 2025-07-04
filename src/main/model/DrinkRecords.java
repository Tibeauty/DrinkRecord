package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// The DrinkRecords class contains a collection of DrinkRecord objects, 
// which indivial drinkrecord is add to the drinkrecords
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
        EventLog.getInstance().logEvent(new Event("Add new drinking record: " + drinkRecord.getType()
                + " " + drinkRecord.getAmount() + "(mL)"));
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

    // EFFECTS: returns true if there are no drink records, false otherwise
    public boolean isEmpty() {
        return this.drinkRecords.isEmpty();
    }

    // EFFECTS: returns the size of the drink records list
    public int size() {
        return this.drinkRecords.size();
    }

    // REQUIRES: !drinkRecords.isEmpty() && index <= drinkRecords.size()
    // EFFECTS: returns the drink record at a specified index
    public DrinkRecord getRecordAtIndex(int index) {
        return this.drinkRecords.get(index);
    }

    // EFFECTS: returns true if there list drinkRecords contains drinkRecord
    public boolean contains(DrinkRecord drinkRecord) {
        return this.drinkRecords.contains(drinkRecord);
    }

    // REQUIRES: drinkRecords.contains(drinkRecord)
    // MODIFIES: this
    // EFFECTS: returns true if there are no drink records, false otherwise
    public void remove(DrinkRecord drinkRecord) {
        this.drinkRecords.remove(drinkRecord);
        EventLog.getInstance().logEvent(new Event("Remove drinking record: " + drinkRecord.getType()
                + " " + drinkRecord.getAmount() + "(mL)"));
    }

    // EFFECTS: returns the drinkrecords list
    public List<DrinkRecord> getDrinkRecords() {
        return this.drinkRecords;
    }

    // EFFECTS: returns things in this DrinkRecords as a JSON array
    public JSONArray drinkRecordsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (DrinkRecord drinkRecord : drinkRecords) {
            jsonArray.put(drinkRecord.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: give positive/negative feedback based on the amount of water and
    // other drinks
    public boolean getFeedback() {
        EventLog.getInstance().logEvent(new Event("Get feedback for drinking records"));
        if (drinkRecords.isEmpty()) {
            return false;
        }
        if (getWaterDrinkAmount() >= getOtherDrinkAmount()) {
            return true;
        } else {
            return false;
        }
    }
    
    // EFFECTS: print out all events in the Eventlog
    public void printLog() {
        System.out.println("Application Event Log:");
        for (Event event : EventLog.getInstance()) {
            System.out.println(event);
            System.out.println("\n");
        }
    }
}
