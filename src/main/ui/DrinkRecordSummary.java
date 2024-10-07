package ui;

import model.DrinkRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DrinkRecordSummary {
    private List<DrinkRecord> drinkRecords;
    private Scanner input;

     // EFFECTS: runs the DrinkRecordSummary application
    public DrinkRecordSummary() {
        runSummary();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runSummary() {
        boolean keepGoing = true;
        String command = null;
        init();

        System.out.println("Welcome to the Water Record App!");
        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    public void processCommand(String command) {

    }
    
    // MODIFIES: this
    // EFFECTS: initalize drinkrecordsummary
    public void init() { 
        this.drinkRecords = new ArrayList<>();
        input = new Scanner(System.in);

    }

    // EFFECTS: displays menu of options to use
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add a new drink record");
        System.out.println("\tv -> View all drink records");
        System.out.println("\tq -> Exit the application");
    }
 
}
