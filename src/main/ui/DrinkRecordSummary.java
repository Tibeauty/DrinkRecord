package ui;

import model.DrinkRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DrinkRecordSummary {
    private List<DrinkRecord> drinkRecords;
    private Scanner input;

    // MODIFIES: this
    // EFFECTS: initalize drinkrecordsummary
    public void init() { 
        this.drinkRecords = new ArrayList<>();
        input = new Scanner(System.in);

    }

}
