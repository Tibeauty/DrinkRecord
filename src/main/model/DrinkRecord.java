package model;

public class DrinkRecord {
    private String type;       //Types of beverages
    private int amount;        //Drink amount of this drinkrecord

    // EFFECTS: constructs a DrinkRecord object 
    public DrinkRecord(String type, int amount) {
        this.type = type;
        this.amount = amount;
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
}
