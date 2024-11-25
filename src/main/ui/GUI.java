package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import model.DrinkRecord;
import model.DrinkRecords;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;
import model.Event;
import model.EventLog;
import java.util.ArrayList;
import java.util.List;



//The GUI class represents the graphical user interface for drinking records;
// Users can interact with the interface
public class GUI implements ActionListener {
    private JLabel label;
    private JFrame frame;
    private JButton addButton;
    private JButton loadButton;
    private JButton feedbackButton;
    private JButton saveButton;
    private JButton removeButton;
    private JPanel panel;
    private DefaultListModel<DrinkRecord> myList;
    private JList<DrinkRecord> list;
    private JScrollPane listScrollPane;
    private DrinkRecords drinkRecords;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/myDrinkRecord.json";
    
    public GUI() {
        initGUI();

        exitShowLog();

        // Add the list and label to the top of the panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(label, BorderLayout.NORTH);
        topPanel.add(listScrollPane, BorderLayout.CENTER);
        panel.add(topPanel, BorderLayout.CENTER);

        // Add the buttons to the bottom of the panel
        JPanel bottomPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        bottomPanel.add(addButton);
        bottomPanel.add(removeButton);
        bottomPanel.add(feedbackButton);
        bottomPanel.add(loadButton);
        bottomPanel.add(saveButton);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Drinking Records");
        frame.setSize(800, 800);
        frame.setVisible(true);
    }

    // EFFECTS: when user quits the application, 
    // print to the console all the events 
    // that have been logged since the application started 
    public void exitShowLog() {
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                printLog();
                System.exit(0);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: initalize GUI and drinkRecords
    public void initGUI() {
        drinkRecords = new DrinkRecords();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        frame = new JFrame();

        label = new JLabel("Add new drinking record to begin");
 

        myList = new DefaultListModel<>();
        list = new JList<>(myList);
        listScrollPane = new JScrollPane(list);

        // Create the buttons
        feedbackButton = new JButton("Get Feedback for your records");
        loadButton = new JButton("Load drinking Records from file");
        addButton = new JButton("Add new drinking Records");
        saveButton = new JButton("Save your drinking Records");
        removeButton = new JButton("Remove selected drink record");

        addButton.addActionListener(this);
        saveButton.addActionListener(this);
        feedbackButton.addActionListener(this);
        loadButton.addActionListener(this);
        removeButton.addActionListener(this);

        panel = new JPanel(new BorderLayout());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            passAddButton();
        } else if (e.getSource() == saveButton) {
            passSaveButton();
        } else if (e.getSource() == loadButton) {
            passLoadButton();
        } else if (e.getSource() == feedbackButton) {
            passFeedbackButton();
        } else if (e.getSource() == removeButton) {
            passRemoveButton();
        }
       
    }

    // EFFECTS: generate next step when user clicking remove button
    public void passRemoveButton() {
        int selectedIndex = list.getSelectedIndex(); 
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a record to remove!");
            return;
        }

        DrinkRecord selectedRecord = myList.get(selectedIndex);

        // change the format of the text COLOR show on the optionPane
        String formattedRecord = String.format(
                    "<html>Are you sure you want to remove this record?<br><br><b style='color:blue;'>%s</b></html>",
                selectedRecord.toString()
        );

        // New dialog of remove
        int confirmation = JOptionPane.showConfirmDialog(
                frame,
                formattedRecord,
                "Confirm Removal",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmation == JOptionPane.YES_OPTION) {
            // Remove from both the JList and drinkRecords
            myList.remove(selectedIndex);
            drinkRecords.remove(selectedRecord);

            JOptionPane.showMessageDialog(frame, "Selected drink record removed successfully!");
        }
    }


    // EFFECTS: generate next step when user clicking getfeedback button
    public void passFeedbackButton() {
        JDialog feedbackDialog = new JDialog(frame, "Feedback", true);
        feedbackDialog.setSize(300, 300);
        feedbackDialog.setLayout(new BorderLayout());

        JLabel feedbackLabel = new JLabel();
        feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        feedbackLabel.setVerticalAlignment(SwingConstants.CENTER);

        if (drinkRecords.getFeedback()) {
            feedbackLabel.setText("Great! You have a healthy drinking record");
            feedbackLabel.setIcon(new ImageIcon(new ImageIcon("images/happy_face.png")
                    .getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        } else {
            feedbackLabel.setText("Try to drink more water! (*X*)");
            feedbackLabel.setIcon(new ImageIcon(new ImageIcon("images/sad_face.png")
                    .getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        }

        feedbackLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        feedbackLabel.setVerticalTextPosition(SwingConstants.BOTTOM);

        feedbackDialog.add(feedbackLabel, BorderLayout.CENTER);

        feedbackDialog.setLocationRelativeTo(frame);
        feedbackDialog.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: generate next step when user clicking save button
    public void passLoadButton() {
        try {
            drinkRecords = jsonReader.read();
            List<DrinkRecord> recordsCopy = new ArrayList<>(drinkRecords.getDrinkRecords());
            for (DrinkRecord drinkRecord : recordsCopy) {
                addNewDrinkRecord(drinkRecord.getType(), drinkRecord.getAmount());
            }
            
            JOptionPane.showMessageDialog(frame, "Successfully loaded my drinking records from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: generate next step when user clicking save button
    public void passSaveButton() {
        try {
            jsonWriter.open();
            jsonWriter.write(drinkRecords);
            jsonWriter.close();
            JOptionPane.showMessageDialog(frame, "Successfully saved my drinking records to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }   
    }

    // MODIFIES: this
    // EFFECTS: generate next step when user clicking add button
    public void passAddButton() {
        JTextField typeField = new JTextField();
        JTextField amountField = new JTextField();
        Object[] message = {
            "Type of drink:", typeField,
            "Amount (ml):", amountField
        };

        ImageIcon originalIcon = new ImageIcon("images/drink.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon customIcon = new ImageIcon(scaledImage);

        int option = JOptionPane.showConfirmDialog(
                frame, 
                message, 
                "Enter Drinking Record", 
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                customIcon
        );

        if (option == JOptionPane.OK_OPTION) {
            String type = typeField.getText();
            String amountText = amountField.getText();
            int amount = Integer.parseInt(amountText);
            addNewDrinkRecord(type, amount);
        }
    }

    // MODIFIES: this
    // EFFECT: add new drink records to JList and drinkRecords
    public void addNewDrinkRecord(String type, int amount) {
        DrinkRecord newDrinkRecord = new DrinkRecord(type, amount);
        drinkRecords.addDrinkRecord(newDrinkRecord);
        myList.addElement(newDrinkRecord);
    }


    private void printLog() {
        System.out.println("Application Event Log:");
        for (Event event : EventLog.getInstance()) {
            System.out.println(event);
            System.out.println("\n");
        }
    }
}
