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
            viewDrinkRecord();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a drink record to the list of drinkRecords
    public void addDrinkRecord() {
        System.out.println("Please enter the drink's type");
        String type = input.nextLine();

        System.out.println("Please enter the drinking amount");
        int amount = input.nextInt();

        DrinkRecord drinkRecord = new DrinkRecord(type, amount);
        this.drinkRecords.add(drinkRecord);
        System.out.println("\nNew drinking record successfully added!");

    }

    // MODIFIES: this
    // EFFECTS: displays all drinkRecords
    public void viewDrinkRecord() {
        //TODO: inplement
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
