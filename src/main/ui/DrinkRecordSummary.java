package ui;

import model.DrinkRecord;
import model.DrinkRecords;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// The DrinkRecordSummary class provides the summary of drinkrecords and interact with
// user input.
public class DrinkRecordSummary {
    private DrinkRecords drinkRecords;
    private Scanner input;
    private int currentRecordIndex = 0;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/myDrinkRecord.json";

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
            displayDrinkRecords();
        } else if (command.equals("w")) {
            waterDrinkRecord();
        } else if (command.equals("o")) {
            othersDrinkRecord();
        } else if (command.equals("s")) {
            saveDrinkRecords();
        } else if (command.equals("l")) {
            loadDrinkRecords();
        } else if (command.equals("f")) {
            getFeedback();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: give positive/negative feedback based on the amount of water and other drinks
    public void getFeedback() {
        if (drinkRecords.isEmpty()) {
            System.out.println("Error: No drinking record to summary. Trying adding a drinking record first!");
            return;
        }

        if (drinkRecords.getWaterDrinkAmount() >= drinkRecords.getOtherDrinkAmount()) {
            System.out.println("Great! You have a healthy drinking record");
            System.out.println("Keep it up! (^v^)");
        } else {
            System.out.println("Sorry, you have a unhealthy drinking record");
            System.out.println("Try to drink more water! (*X*)");
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
        drinkRecords.addDrinkRecord(drinkRecord);
        System.out.println("\nNew drinking record successfully added!");

    }


    // MODIFIES: this
    // EFFECTS: display exist list of drink records and handles inputs to related to viewing the drinkRecords
    public void displayDrinkRecords() {
        if (drinkRecords.isEmpty()) {
            System.out.println("Error: No drinking record to summary. Trying adding a drinking record first!");
            return;
        }

        displayViewMenu();
        String command = "";
        while (!command.equals("q") && drinkRecords.size() > 0) {
            DrinkRecord currentDrinkRecord = drinkRecords.getRecordAtIndex(this.currentRecordIndex);
            displayDrinkRecord(currentDrinkRecord);
            command = input.nextLine();
            handleViewCommands(command);
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
    public void handleViewCommands(String command) {
        System.out.print("\n");

        DrinkRecord currentDrinkRecord = drinkRecords.getRecordAtIndex(currentRecordIndex);
        if (command.equals("s")) {
            displayDrinkAmount(currentDrinkRecord);
        } else if (command.equals("a")) {
            addDrinkAmount(currentDrinkRecord);
        } else if (command.equals("r")) {
            removeDrinkRecord(currentDrinkRecord);
        } else if (command.equals("n")) {
            getNextDrinkRecord();
        } else if (command.equals("p")) {
            getPreviousDrinkRecord();
        } else if (command.equals("q")) {
            System.out.println("Returning to the menu...");
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: if there is water record, display the water amount
    public void waterDrinkRecord() {
        if (drinkRecords.getWaterDrinkAmount() == 0) {
            System.out.println("You haven't been drinking any water !!");
        } else {
            System.out.println("Great! You have been drinking " + drinkRecords.getWaterDrinkAmount() + " mL water");
        }
    }


    // EFFECTS: if there's other drinks (non-water), display all the amount of other drinks
    public void othersDrinkRecord() {
        if (drinkRecords.getOtherDrinkAmount() == 0) {
            System.out.println("You haven't had anything other than water !!");
        } else {
            System.out.println("You have been drinking " + drinkRecords.getOtherDrinkAmount() + " mL other drinks");
        }
    }

    // MODIFIES: this
    // EFFECTS: remove give drinkRecord from the list of drinkRecords
    public void removeDrinkRecord(DrinkRecord drinkRecord) {
        if (drinkRecords.contains(drinkRecord)) {
            drinkRecords.remove(drinkRecord);
            currentRecordIndex -= 1;
            System.out.println("The drinking record has been successfully removed.");
        } else {
            System.out.println("The drinking record was not found.");
        }
    }

    // MODIFIES: this
    // EFFECTS: add given amount to the current drink record
    public void addDrinkAmount(DrinkRecord drinkRecord) {
        System.out.println("Please enter the amount you want add for " + drinkRecord.getType());
        int additionalAmount = input.nextInt();
        drinkRecord.addAmount(additionalAmount);
    }


    // EFFECTS：display the drinking amount of the given type of drink
    public void displayDrinkAmount(DrinkRecord drinkRecord) {
        System.out.println("You drank " + drinkRecord.getAmount() + " of " + drinkRecord.getType());
    }

    // MODIFIES: this
    // EFFECTS: if there exists another drink record to display, increments the current drinkrecord index
    public void getNextDrinkRecord() {
        if (this.currentRecordIndex >= drinkRecords.size() - 1) {
            System.out.println("Error: No more drinking record to display");
        } else {
            this.currentRecordIndex++;
        }
    }

    // MODIFIES: this
    // EFFECTS: if there exists previous drink record to display, decrements the current drinkrecord index
    public void getPreviousDrinkRecord() {
        if (this.currentRecordIndex <= 0) {
            System.out.println("Error: No more previous drinking record to display");
        } else {
            this.currentRecordIndex--;
        }
    }
    
    // EFFECTS: displays a list of commands that can be used in the view drink records menu
    public void displayViewMenu()  {
        System.out.println("Enter 's' to show the amount of this drink");
        System.out.println("Enter 'a' to add drinking amount to this drink.");
        System.out.println("Enter 'r' to remove this drinking record");
        System.out.println("Enter 'n' to move to the next flashcard.");
        System.out.println("Enter 'p' to move to the previous flashcard.");
        System.out.println("Enter 'q' to return to the menu");
    }

    // MODIFIES: this
    // EFFECTS: initalize drinkrecordsummary
    public void init() { 
        this.drinkRecords = new DrinkRecords();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        input = new Scanner(System.in);

    }

    // EFFECTS: displays menu of options to use
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add a new drink record");
        System.out.println("\tv -> View all drink records");
        System.out.println("\tw -> View total amount of water drink record");
        System.out.println("\to -> View total amout of other drink records");
        System.out.println("\ts -> Save drink records room to file");
        System.out.println("\tl -> Load drink records room from file");
        System.out.println("\tf -> Get a feedback for your drinking records");
        System.out.println("\tq -> Exit the application");
    }

     // EFFECTS: saves the drink records to file
    private void saveDrinkRecords() {
        try {
            jsonWriter.open();
            jsonWriter.write(drinkRecords);
            jsonWriter.close();
            System.out.println("Saved my drinking records to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads drink records from file
    private void loadDrinkRecords() {
        try {
            drinkRecords = jsonReader.read();
            System.out.println("Loaded my drinking record from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
 
}
