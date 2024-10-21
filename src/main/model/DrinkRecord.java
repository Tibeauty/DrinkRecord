package model;

// The DrinkRecord class represents a record of a beverage consumption event.
// Each record contains information about the type of beverage consumed and 
// the amount of the beverage. 
public class DrinkRecord {
    private String type;       //Types of beverages
    private int amount;        //Drink amount of this drinkrecord

    // EFFECTS: constructs a DrinkRecord object based on its type
    public DrinkRecord(String type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    // REQUIRES: additionalAmount > 0
    // MODIFIES: this
    // EFFECTS: increase the amount by the specified value
    public void addAmount(int additionalAmount) {
        amount += additionalAmount;
    }

    // MODIFIES: this
    // EFFECTS: sets the DrinkRecord type to the new, given type
    public void setType(String newtype) {
        type = newtype;
    }

    // MODIFIES: this
    // EFFECTS: sets the DrinkRecord amount to the new, given amount
    public void setAmount(int newAmount) {
        amount = newAmount;
    }

    // EFFECTS:  returns the DrinkRecord's type
    public String getType() {
        return type;
    }

    // EFFECTS:  returns the DrinkRecord's amount
    public int getAmount() {
        return amount;
    }

}
