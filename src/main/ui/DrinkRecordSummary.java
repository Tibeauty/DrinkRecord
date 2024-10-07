package ui;

import model.DrinkRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DrinkRecordSummary {
    private List<DrinkRecord> drinkRecords;
    private Scanner input;

    private int currentRecordIndex = 0;

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

        System.out.println("Welcome to the Drinking Record App!");
        while (keepGoing) {
            displayMenu();
            command = input.nextLine();
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
        if (command.equals("a")) {
            addDrinkRecord();
        } else if (command.equals("v")) {
            viewDrinkRecords();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a drink record to the list of drinkRecords
    public void addDrinkRecord() {
        System.out.println("Please enter the drink's type");
        String type = input.nextLine();

        System.out.println("Please enter the drinking amount (mL)");
        int amount = input.nextInt();

        DrinkRecord drinkRecord = new DrinkRecord(type, amount);
        this.drinkRecords.add(drinkRecord);
        System.out.println("\nNew drinking record successfully added!");

    }

    // MODIFIES: this
    // EFFECTS: view all drinkRecords
    public void viewDrinkRecords() {
        displayDrinkRecords(this.drinkRecords);
    }

    // MODIFIES: this
    // EFFECTS: display exist list of drink records and handles inputs to related to viewing the drinkRecords
    public void displayDrinkRecords(List<DrinkRecord> drinkRecords) {
        if (drinkRecords.isEmpty()) {
            System.out.println("Error: No drinking record to summary. Trying adding a drinking record first!");
            return;
        }

        displayViewMenu();
        String command = null;
        while (!command.equals("q")) {
            DrinkRecord currentDrinkRecord = drinkRecords.get(this.currentRecordIndex);
            displayDrinkRecord(currentDrinkRecord);
            command = input.nextLine();
            handleViewCommands(command, drinkRecords);
        }
        this.currentRecordIndex = 0;
        
    }
 
    // EFFECTS: display the given drinking record
    public void displayDrinkRecord(DrinkRecord drinkRecord) {
        System.out.println("-----------------------------------");
        System.out.println("Drinking Record #" + (this.currentRecordIndex + 1));
        System.out.println(drinkRecord.getType());
    }

    // MODIFIES: this
    // EFFECTS: process the user's command in the view drink record menu
    public void handleViewCommands(String command, List<DrinkRecord> drinkRecords) {
        System.out.print("\n");

        DrinkRecord currentDrinkRecord = drinkRecords.get(currentRecordIndex);
        if (command.equals("s")) {
            displayDrinkAmount(currentDrinkRecord);
        } else if (command.equals("n")) {
            getNextDrinkRecord(drinkRecords);
        } else if (command.equals("p")) {
            getPreviousDrinkRecord(drinkRecords);
        } else if (command.equals("q")) {
            System.out.println("Returning to the menu...");
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS：display the drinking amount of the given type of drink
    public void displayDrinkAmount(DrinkRecord drinkRecord) {
        System.out.println("You drank " + drinkRecord.getAmount() + " of " + drinkRecord.getType());
    }

    // MODIFIES: this
    // EFFECTS: if there exists another drink record to display, increments the current drinkrecord index
    public void getNextDrinkRecord(List<DrinkRecord> drinkRecords) {
        if (this.currentRecordIndex >= drinkRecords.size() - 1) {
            System.out.println("Error: No more drinking record to display");
        } else {
            this.currentRecordIndex++;
        }
    }

    // MODIFIES: this
    // EFFECTS: if there exists previous drink record to display, decrements the current drinkrecord index
    public void getPreviousDrinkRecord(List<DrinkRecord> drinkRecords) {
        if (this.currentRecordIndex <= 0) {
            System.out.println("Error: No more previous drinking record to display");
        } else {
            this.currentRecordIndex--;
        }
    }
    
    // EFFECTS: displays a list of commands that can be used in the view drink records menu
    public void displayViewMenu()  {
        System.out.println("Enter 's' to show the amount of this drink");
        System.out.println("Enter 'n' to move to the next flashcard.");
        System.out.println("Enter 'p' to move to the previous flashcard.");
        System.out.println("Enter 'q' to return to the menu");
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
        System.out.println("\tf -> Get a feedback for your drinking records");
        System.out.println("\tq -> Exit the application");
    }
 
}
