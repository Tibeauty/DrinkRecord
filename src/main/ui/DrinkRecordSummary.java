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

        System.out.println("Please enter the drinking amount");
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
    }

    // EFFECTS: displays a list of commands that can be used in the view drink records menu
    public void displayViewMenu()  {
        System.out.println("Enter 's' to select one type of drink");
        System.out.println("Enter 'f' to get a feedback for your drinking records");
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
